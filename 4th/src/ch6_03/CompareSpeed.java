package ch6_03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Q：1,000個のスレッドを生成し、各スレッドは、ある一つのカウンターを100,000回だけ1ずつ増加させます。AtomicLong と
 * LongAdder を使用した場合の性能を比較しなさい。
 *
 * A：明らかにLongAdderの方が速い。
 */
public class CompareSpeed {

	public static void main(String[] args) throws InterruptedException {
		int i = 0;

		System.out.println("incrementAtomicLong");
		for (i = 0; i < 10; i++) {
			CompareSpeed.incrementAtomicLong();
		}

		System.out.println("IncrementLongAdder");
		for (i = 0; i < 10; i++) {
			CompareSpeed.IncrementLongAdder();
		}
		/**
		 * <Pre>
		incrementAtomicLong
		経過時間 : 2.730701498秒, カウンタ : 100000000
		経過時間 : 2.362610464秒, カウンタ : 100000000
		経過時間 : 2.363227658秒, カウンタ : 100000000
		経過時間 : 3.219736393秒, カウンタ : 100000000
		経過時間 : 2.018224245秒, カウンタ : 100000000
		経過時間 : 3.158997617秒, カウンタ : 100000000
		経過時間 : 3.152628345秒, カウンタ : 100000000
		経過時間 : 2.836916215秒, カウンタ : 100000000
		経過時間 : 2.361422727秒, カウンタ : 100000000
		経過時間 : 3.164087717秒, カウンタ : 100000000
		IncrementLongAdder
		経過時間 : 0.257425336秒, カウンタ : 100000000
		経過時間 : 0.211903677秒, カウンタ : 100000000
		経過時間 : 0.215427233秒, カウンタ : 100000000
		経過時間 : 0.217794309秒, カウンタ : 100000000
		経過時間 : 0.227167910秒, カウンタ : 100000000
		経過時間 : 0.220629762秒, カウンタ : 100000000
		経過時間 : 0.224658549秒, カウンタ : 100000000
		経過時間 : 0.214043095秒, カウンタ : 100000000
		経過時間 : 0.217847957秒, カウンタ : 100000000
		経過時間 : 0.224469146秒, カウンタ : 100000000
		 * </Pre>
		 */
	}

	static public void incrementAtomicLong() throws InterruptedException {

		final int TASK = 1000;
		final int COUNT = 100_000;

		AtomicLong globalCounter = new AtomicLong(0);
		CountDownLatch latch = new CountDownLatch(TASK);
		ExecutorService pool = Executors.newCachedThreadPool();

		long startTime = System.nanoTime();

		for (int n = 0; n < TASK; n++) {
			pool.submit(() -> {
				for (int i = 0; i < COUNT; i++) {
					globalCounter.incrementAndGet();
				}
				latch.countDown();
			});
		}
		pool.shutdown();
		// カウントダウンが完了するまで待つ。スレッドの終了を待つ。
		latch.await();
		System.out.println("経過時間 : " + (System.nanoTime() - startTime) / 1E9 + "秒, " + "カウンタ : " + globalCounter);
	}

	static public void IncrementLongAdder() throws InterruptedException {
		final int TASK = 1000;
		final int COUNT = 100_000;

		LongAdder globalCounter = new LongAdder();
		CountDownLatch latch = new CountDownLatch(TASK);
		ExecutorService pool = Executors.newCachedThreadPool();
		long startTime = System.nanoTime();
		for (int n = 0; n < TASK; n++) {
			pool.submit(() -> {
				for (int i = 0; i < COUNT; i++) {
					globalCounter.increment();
				}
				latch.countDown();
			});
		}
		pool.shutdown();
		// カウントダウンが完了するまで待つ。スレッドの終了を待つ。
		latch.await();
		System.out.println("経過時間 : " + (System.nanoTime() - startTime) / 1E9 + "秒, " + "カウンタ : " + globalCounter);
	}
}
