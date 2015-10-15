package ch6_04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * Q：LongAccumulatorを使用して、要素の最大値あるいは最小値を計算しなさい。
 *
 */
public class Hoge {

	public static void main(String[] args) throws InterruptedException {

		// 測定対象の要素を生成。
		long[] values = new long[1000];
		for (int i = 0; i < values.length; i++) {
			// 0-999以内の数値を格納する。
			values[i] = (long) (1000 * Math.random());
		}

		final int TASK = 100;
		final int COUNT = 100;

		// 最大値を求めるので、初期値は最小値。サンプルが0以上なので、0Lでも良い。
		LongAccumulator max = new LongAccumulator(Math::max, Long.MIN_VALUE);
		CountDownLatch latch = new CountDownLatch(TASK);
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int n = 0; n < TASK; n++) {
			int start = COUNT * n;
			int end = Math.min(start + COUNT, values.length);
			pool.submit(() -> {
				for (int i = start; i < end; i++) {
					max.accumulate(values[i]);
				}
				latch.countDown();
			});
		}
		pool.shutdown();
		latch.await();
		System.out.println("最大値 : " + max);
	}

}
