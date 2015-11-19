package ch8_05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <Pre>
 * Q：第2章の初めで、リスト内の長い単語をwords.stream().filter(w->w.length()>12).count()で数えました。
 * ラムダ式を用いて、ストリームを使用しないで、同じことを行いなさい。
 *
 * A：カウント数は1946。10回繰り返して観測した結果、ストリームのほうが速かった。
 * ----(結果)--------
No Stream:start... -> Time[msec] : 20.495687
No Stream:start... -> Time[msec] : 10.586996
No Stream:start... -> Time[msec] : 4.288168
No Stream:start... -> Time[msec] : 3.440518
No Stream:start... -> Time[msec] : 3.857578
No Stream:start... -> Time[msec] : 3.529155
No Stream:start... -> Time[msec] : 3.7904
No Stream:start... -> Time[msec] : 2.83312
No Stream:start... -> Time[msec] : 2.819591
No Stream:start... -> Time[msec] : 3.331354
Stream:start... -> Time[msec] : 12.923283
Stream:start... -> Time[msec] : 5.39893
Stream:start... -> Time[msec] : 2.525223
Stream:start... -> Time[msec] : 2.416059
Stream:start... -> Time[msec] : 2.503764
Stream:start... -> Time[msec] : 2.981004
Stream:start... -> Time[msec] : 2.313894
Stream:start... -> Time[msec] : 2.319492
Stream:start... -> Time[msec] : 2.453847
Stream:start... -> Time[msec] : 2.577473
 * </Pre>
 */
public class WordCounterEx {
	private int threshold = 8;

	WordCounterEx(int val) {
		this.threshold = val;
	}

	/*
	 * Stream利用しない場合
	 */
	void counter(List<String> words) {
		System.out.print("No Stream:start...");
		timer(() -> {
			words.removeIf(w -> w.length() <= threshold);
			// System.out.println(" -> Count : " + words.size());
		});
	}

	void counter(Stream<String> stream) {
		System.out.print("Stream:start...");

		timer(() -> {
			final long result = stream.filter(w -> w.length() > threshold).count();
			// System.out.println(" -> Count : " + result);
		});
	}

	private void timer(final Runnable action) {
		final long start = System.nanoTime();
		action.run();
		final long end = System.nanoTime();
		System.out.println(" -> Time[msec] : " + (double) (end - start) / 1_000_000.0);
	}

	public static void main(String[] args) {
		try {
			String contents = new String(Files.readAllBytes(Paths.get("src/ch8_05/war-and-peace.txt")),
					StandardCharsets.UTF_8);

			WordCounterEx counter = new WordCounterEx(12);
			for (int i = 0; i < 10; i++) {
				List<String> words = new ArrayList<String>(Arrays.asList(contents.split("[\\P{L}]+")));
				counter.counter(words);
			}
			for (int i = 0; i < 10; i++) {
				List<String> words = new ArrayList<String>(Arrays.asList(contents.split("[\\P{L}]+")));
				Stream<String> stream = words.stream();
				counter.counter(stream);
			}

		} catch (IOException e) {
			System.out.println("ファイルが開けません。");
		}
	}

}
