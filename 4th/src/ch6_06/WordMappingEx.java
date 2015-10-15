package ch6_06;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <Pre>
 * Q：マップを使用するメソッドとして、mergeの代わりにcomputeIfAbsentを使用して、練習問題5と同じアプリケーションを作成しなさい。
 * この方法の利点は何ですか？
 * A：computeIfAbsentの方は、Setを必要な場合にのみ作れば良いため、CPU&メモリリソースの効率が良い。
 * ○mergeの場合
 * まずSet作成→mergeをトライ→既に登録があれば再マッピング関数でValueを計算
 * ○computeIfAbsentの場合
 * computeIfAbsent実行→初めてならSet作成→名前の追加(重複のチェックはSetにお任せ)
 *
 * &#64;see http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/ConcurrentHashMap.html#computeIfAbsent-K-java.util.function.Function-
 * </Pre>
 */
public class WordMappingEx implements Closeable {

	private final BufferedReader reader;
	private List<String> words = Collections.emptyList();

	public WordMappingEx(File file) throws IOException {
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
	public WordMappingEx(File file, Charset charset) throws IOException {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
	}

	public void close() throws IOException {
		reader.close();
	}

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
				try (WordMappingEx reader = new WordMappingEx(file)) {

					System.out.println("file:" + file.toString());

					String word;
					while ((word = reader.getWord()) != null) {
						// mergeの代わりにcomputeIfAbsentを使用する。
						// computeIfAbsentは、mapのValueにあたる型(ここではSet<File>)を返すので、そこにaddすればよい。
						// mergeと違って、マップのValueの編集をプログラマがやっているため、プログラマがValue部分の排他を気にしなければならない。
						map.computeIfAbsent(word, key -> {
							// mapにValueが設定されていない場合に実行される。
							return ConcurrentHashMap.newKeySet();
						}).add(file);
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
			WordMappingEx.updateWordMap("src/ch6_05/.");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
