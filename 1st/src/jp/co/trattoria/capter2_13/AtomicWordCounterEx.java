package jp.co.trattoria.capter2_13;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtomicWordCounterEx {

	public static void main(String[] args) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\war-and-peace.txt")), StandardCharsets.UTF_8);
			List<String> temp = Arrays.asList(contents.split("[\\P{L}]+"));

			Stream<String> words = temp.stream();

			int threshold = 12;
			AtomicInteger[] shortWords = new AtomicInteger[threshold];
			Arrays.setAll(shortWords, n -> new AtomicInteger(0));

			Map<Integer, Long> wordsMap = words.parallel().filter(
					s->(s.length() < threshold))
			.collect(Collectors.groupingBy(String::length, Collectors.counting()));

			for(int i=0; i<shortWords.length;i++){
				System.out.println(i + " : " + wordsMap.get(i));
			}

		} catch (IOException e) {
			System.out.println("ファイルが開けません");
		}
	}
}
