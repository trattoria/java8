package jp.co.trattoria.chapter1_6;

import java.util.concurrent.Callable;

public class UncheckCallable {
	/**
	 * Runnable.runメソッドの例外を読み捨てバージョンの実相。
	 * @param runner
	 * @return
	 */
	public static Runnable uncheck(Callable<?> runner) {

		return ()->{
			try {
				runner.call();
			} catch (Exception e) {
				// 全ての例外を読み捨て
//				e.printStackTrace();
			}
		};
	}
}
