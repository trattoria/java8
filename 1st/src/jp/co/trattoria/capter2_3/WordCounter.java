package jp.co.trattoria.capter2_3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * 以下の条件において、測定を実施。
 * 文字数：3945
 * 単語数：625語
 *
 * 結果
 * 所要時間 : 60msec : count=101
 * 所要時間 : 5msec : count=101
 *
 */
public class WordCounter {

	private int threshold = 8;

	WordCounter(int val){
		this.threshold = val;
	}

	long counter(List<String> words){
		long count = words.stream().filter(w -> w.length() > threshold).count();
		return count;
	}

	long parallelCounter(List<String> words){
		long count = words.parallelStream().filter(w -> w.length() > threshold).count();
		return count;
	}

	public static void main(String[] args) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\sample.txt")), StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			long start = 0;
			long end = 0;
			long count = 0;

			WordCounter counter = new WordCounter(8);
			start = System.nanoTime();
			count = counter.counter(words);
			end = System.nanoTime();
			System.out.println("所要時間 : " + (end - start) / (1000 * 1000) + "msec : count=" + count);

			counter = new WordCounter(8);
			start = System.nanoTime();
			count = counter.parallelCounter(words);
			end = System.nanoTime();
			System.out.println("所要時間 : " + (end - start) / (1000 * 1000) + "msec : count=" + count);



		} catch (IOException e) {
			System.out.println("ファイルが開けません。");
		}
	}
}
