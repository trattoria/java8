package jp.co.trattoria.chapter2_1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Q１：
 * A：
 *
 *
 *
 * Q２：各スレッドが単一のカウンタを更新するような実装にはしたくないはずである。何故か?
 * A：インクリメントを正しく行うためには、スレッド毎の加算処理を排他して、アトミックな処理であることを保証しなければいけないため。
 */
public class WordCounter {

	private int threshold = 8;

	int oldCounter(List<String> words){
		int count = 0;
		for(String w : words){
			if(w.length() > threshold){
				count++;
			}
		}
		return count;
	}

	int oldParallelCounter(List<String> words){
		int count = 0;
		for(String w : words){
			if(w.length() > threshold){
				count++;
			}
		}
		return count;
	}

	long newCounter(List<String> words){
		long count = words.stream().filter(w -> w.length() > threshold).count();
		return count;
	}

	long newParallelCounter(List<String> words){
		long count = words.parallelStream().filter(w -> w.length() > threshold).count();
		return count;
	}



	public static void main(String[] args) throws IOException {

		String contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\sample.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		WordCounter counter = new WordCounter();

		long count = 0;
		count = counter.oldCounter(words);
		System.out.println("result:" + count);

		count = counter.newCounter(words);
		System.out.println("result:" + count);

		count = counter.newParallelCounter(words);
		System.out.println("result:" + count);

	}

}
