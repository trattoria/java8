package jp.co.trattoria.capter2_3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 測定手段以上に、別のボトルネックがありそう・・・。
 *
 * 以下の条件において、測定を実施。
 * 文字数：167463
 * 単語数：21894語
 * 結果:評価対象のテキストが短すぎて誤差にしかならない・・・
 * 所要時間 : 77msec : count=2615	最初の実行のため、オーバーヘッドが大きい
 * 所要時間 : 6msec : count=2615
 * 所要時間 : 2msec : count=2615
 * 所要時間 : 0msec : count=2615
 * 所要時間 : 0msec : count=2615
 * 所要時間 : 0msec : count=2615
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
		List<String> words;
		try {

			long start = 0;
			long end = 0;
			long count = 0;
			long result = 0;
			WordCounter counter;

			System.out.println("start");

			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\rfc793_2.txt")), StandardCharsets.UTF_8);
			words = Arrays.asList(contents.split("[\\P{L}]+"));

			counter = new WordCounter(8);
			start = System.nanoTime();
			count = counter.parallelCounter(words);
			end = System.nanoTime();
			result = (end - start)/ (1000 * 1000);
			System.out.println("所要時間 : " + result + "msec : count=" + count);


			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\rfc793_1.txt")), StandardCharsets.UTF_8);
			words = Arrays.asList(contents.split("[\\P{L}]+"));

			counter = new WordCounter(8);
			start = System.nanoTime();
			count = counter.counter(words);
			end = System.nanoTime();
			result = (end - start)/ (1000 * 1000);
			System.out.println("所要時間 : " + result  + "msec : count=" + count);

			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\rfc793_2.txt")), StandardCharsets.UTF_8);
			words = Arrays.asList(contents.split("[\\P{L}]+"));

			counter = new WordCounter(8);
			start = System.nanoTime();
			count = counter.parallelCounter(words);
			end = System.nanoTime();
			result = (end - start)/ (1000 * 1000);
			System.out.println("所要時間 : " + result + "msec : count=" + count);


		} catch (IOException e) {
			System.out.println("ファイルが開けません。");
		}
	}
}
