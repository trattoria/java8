package jp.co.trattoria.chapter1_6;

import org.junit.Test;

import jp.co.trattoria.chapter1_6.UncheckCallable;

public class UncheckCallableTest {

	@Test
	public void testrun() {
		new Thread(UncheckCallable.uncheck(()->{
			System.out.println("Zzz");
			Thread.sleep(1000);
			return null;	// Callableは返り値が必要！！
		})).start();
	}

}
