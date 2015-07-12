package jp.co.trattoria.capter2_1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCounterEx implements Runnable{

	static final AtomicInteger totalCount = new AtomicInteger(0);
	static 	List<String> words = null;

    public AtomicBoolean isFinish = new AtomicBoolean(false);
	private static int threshold = -1;
	private static int threadCount = -1;
	private int id;

	/**
	 * 1トランザクション毎に検索範囲を設定して、スレッドに割り当てて並列処理する。
	 * ・捜査対象を登録する機能
	 * ・レンジを設定する機能
	 * ・カウントする機能
	 */
	public static void setPreSetting(List<String> words, int threshold){
		WordCounterEx.words = words;
		WordCounterEx.threshold = threshold;
	}

	public static void startCount(int threadCount){

		if(WordCounterEx.words == null || WordCounterEx.threshold < 0 || threadCount < 0){
			throw new IllegalArgumentException("引数がなんかダメ");
		}

		WordCounterEx.threadCount = threadCount;

		for(int i = 0; i<WordCounterEx.threadCount; i++){
			WordCounterEx counter = new WordCounterEx(i);
			counter.run();
		}
	}

	/**
	 * コンストラクタで、捜査対象を登録する機能、レンジを設定する機能を提供する。
	 * @param range
	 * @param id
	 */
	WordCounterEx(int id){
		this.id = id;
	}

	@Override
	public void run() {

		int from = WordCounterEx.threadCount * this.id;
		int to = Math.min(WordCounterEx.threadCount * (this.id+1), WordCounterEx.words.size());

		int count = 0;
		for (int i = from; i < to; i++) {
			if (words.get(i).length() > WordCounterEx.threshold){
				count++;
			}
		}
		totalCount.addAndGet(count);

		isFinish.set(true);
	}

	public static void main(String[] args) throws IOException {

		String contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\sample.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		WordCounterEx.setPreSetting(words, 8);
		WordCounterEx.startCount(100);
		System.out.println("counter:" + WordCounterEx.totalCount);
	}

}
