package ch6_05;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Q：複数スレッドが複数のファイルから全ての単語を読み込むアプリケーションを書きなさい。
 *
 */
public class WordMapping implements Closeable {

	private final BufferedReader reader;
	private List<String> words = Collections.emptyList();

	public WordMapping(File file) throws IOException {
		this(file, StandardCharsets.UTF_8);
	}

	/**
	 * ファイルを読み込む。
	 *
	 * @param file
	 *            ファイルオブジェクト。
	 * @param charset
	 *            キャラセット。
	 * @throws IOException
	 *             ファイルアクセス失敗。
	 */
	public WordMapping(File file, Charset charset) throws IOException {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
	}

	public void close() throws IOException {
		reader.close();
	}

	// ファイルの終端まで達するとnullを返す。
	public String getWord() throws IOException {

		while (words.isEmpty()) {
			String line = reader.readLine();
			if (line == null) {
				return null;
			}
			words = new LinkedList<String>(Arrays.asList(line.split("[\\P{L}]+")));
		}

		// wordリストが空になったっら新しい行を読み込む。
		return words.remove(0);
	}

	public static void updateWordMap(String path) throws InterruptedException {

		ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

		File[] files = new File(path).listFiles(f -> f.getName().endsWith(".txt"));
		CountDownLatch latch = new CountDownLatch(files.length);

		ExecutorService pool = Executors.newCachedThreadPool();

		for (File file : files) {
			pool.submit(() -> {
				try (WordMapping reader = new WordMapping(file)) {

					System.out.println("file:" + file.toString());

					String word;
					while ((word = reader.getWord()) != null) {
						Set<File> set = new HashSet<File>();
						set.add(file);
						map.merge(word, set, (existing, newItem) -> {
							// wordに対して値が存在した時は、値のSetに新しいファイル名を追加する。
							existing.addAll(newItem);
							return existing;
						});
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				latch.countDown();
			});
		}
		pool.shutdown();
		latch.await();

		Collection<String> keys = map.keySet();
		for (String s : keys) {
			System.out.println(s + ":" + map.get(s));
		}
	}

	public static void main(String[] args) {
		try {
			WordMapping.updateWordMap("src/ch6_05/.");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
