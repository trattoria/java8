package jp.co.trattoria.chapter2_12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AtomicWordCounter {

	public static void main(String[] args) {
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\war-and-peace.txt")), StandardCharsets.UTF_8);
			List<String> temp = Arrays.asList(contents.split("[\\P{L}]+"));

			Stream<String> words = temp.stream();

			int threshold = 12;
			AtomicInteger[] shortWords = new AtomicInteger[threshold];
			Arrays.setAll(shortWords, n -> new AtomicInteger(0));

			words.parallel().forEach(s->{
				if(s.length() < threshold){
					shortWords[s.length()].getAndIncrement();
				}
			});

			for(int i=0; i<shortWords.length;i++){
				System.out.println(i + " : " + shortWords[i].get());
			}
//			System.out.println("カウント: " + Arrays.toString(shortWords));

		} catch (IOException e) {
			System.out.println("ファイルが開けません");
		}
	}
}
