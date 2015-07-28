package jp.co.trattoria.chapter1_6;

public class UncheckRunnable{

	/**
	 * Runnable.runメソッドの例外を読み捨てバージョンの実相。
	 * @param runner
	 * @return
	 */
	public static Runnable uncheck(RunnableEx runner) {

		return ()->{
			try {
				runner.run();
			} catch (Exception e) {
				// 全ての例外を読み捨て
//				e.printStackTrace();
			}
		};
	}
}
