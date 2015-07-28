package jp.co.trattoria.chapter1_6;

import org.junit.Test;

import jp.co.trattoria.chapter1_6.UncheckRunnable;

public class UncheckRunnableTest {

	@Test
	public void testrun() {
		new Thread(UncheckRunnable.uncheck(()->{
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();
	}

	@Test
	public void 例外発生時に例外が上がらないこと() {
		new Thread(UncheckRunnable.uncheck(()->{
			System.out.println("Zzz");
			throw new RuntimeException("強制的に例外を発生");
		})).start();
	}
}
