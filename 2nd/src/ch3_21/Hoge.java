package ch3_21;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * Q：staticメソッドである<T, U> Future<U> map(Future<T>, Function<T, U>)を提供しなさい。
 *
 *
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/stream/Collectors.html
 * @see http://aoking.hatenablog.jp/entry/20120319/1332153595
 */
public class Hoge {

	public static <T, U> Future<U> map(Future<T> future, Function<T, U> mapper) {

		return new Future<U>() {
			// タスクの実行の取消しを試みます
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			// タスクが正常に完了する前に取り消された場合はtrueを返します
			public boolean isCancelled() {
				return future.isCancelled();
			}

			// このタスクが完了した場合はtrueを返します
			public boolean isDone() {
				return future.isDone();
			}

			// 必要に応じて計算が完了するまで待機し、その後、計算結果を取得します
			public U get() throws InterruptedException, ExecutionException {
				return mapper.apply(future.get());
			}

			// 計算結果の取得。タイムアウト指定あり
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return mapper.apply(future.get(timeout, unit));
			}
		};
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("task start");
		Future<String> future = service.submit(new Task());
		Future<Integer> length = Hoge.map(future, s -> s.length());
		System.out.println(length.get());
		System.out.println("task end");
		service.shutdown();
	}

}

class Task implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		System.out.println("task executed!");
		return "success";
	}
}