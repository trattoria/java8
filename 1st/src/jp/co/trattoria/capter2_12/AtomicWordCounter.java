package jp.co.trattoria.capter2_12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AtomicWordCounter {

	private int threshold = 8;

	AtomicWordCounter(int val){
		this.threshold = val;

	}

	long parallelCounter(List<String> words){
		long count = words.parallelStream().filter(w -> w.length() < threshold).count();
		return count;
	}

	public static void main(String[] args) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\rfc793_1.txt")), StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			long start = 0;
			long end = 0;
			long count = 0;

			AtomicWordCounter counter = new AtomicWordCounter(8);
			start = System.nanoTime();
			count = counter.parallelCounter(words);
			end = System.nanoTime();
			System.out.println("所要時�: " + (end - start) / (1000 * 1000) + "msec : count=" + count);

		} catch (IOException e) {
			System.out.println("ファイルが開けません�);
		}
	}
}
