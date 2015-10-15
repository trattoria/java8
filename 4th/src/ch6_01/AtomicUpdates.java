package ch6_01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Q：多数のスレッドが更新する最大長の文字列を管理するプログラムを書きなさい。 AtomicReferenceと適切な累積関数を使用しなさい。
 *
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/atomic/
 *      AtomicReference.html
 */
public class AtomicUpdates {

	public static void main(String[] args) {

		try {
			String contents = new String(Files.readAllBytes(Paths.get("src/ch6_01/war-and-peace.txt")),
					StandardCharsets.UTF_8);

			String[] words = contents.split("[\\P{L}]+");

			// 一つのタスク当たりのカウント数(固定)を基準に、タスク数(変動)を決定する。
			final int COUNT = 5000;
			// ceilで切り上げ。
			int nTask = (int) Math.ceil(words.length / (double) COUNT);

			AtomicReference<String> longestWord = new AtomicReference<String>("");
			ExecutorService pool = Executors.newCachedThreadPool();

			for (int n = 0; n < nTask; n++) {
				int start = COUNT * n;
				int end = Math.min(start + COUNT, words.length);

				// 指定した範囲について最長文字列をあぷでーとする。
				pool.submit(() -> {
					for (int i = start; i < end; i++) {
						String word = words[i];
						longestWord.updateAndGet(w -> word.length() > w.length() ? word : w);
					}
				});
			}

			// シャットダウン開始。これ以降、新規タスクは実行できない。
			pool.shutdown();

			// シャットダウン完了待ち。
			pool.awaitTermination(10, TimeUnit.SECONDS);

			System.out.println("最長の単語 : " + longestWord);

		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}

	}

}
