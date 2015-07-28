package jp.co.trattoria.chapter2_2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WordCounter {

	private int threshold = 8;

	WordCounter(int val){
		this.threshold = val;
	}

	void limit5(List<String> words){
		words.stream()
				.filter(w -> w.length() > threshold)
				.limit(5)
				.forEach(System.out::println);
	}

	public static void main(String[] args) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\sample.txt")), StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			WordCounter counter = new WordCounter(8);

			counter.limit5(words);

		} catch (IOException e) {
			System.out.println("ファイルが開けません。");
		}
	}

}
