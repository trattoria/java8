package jp.co.trattoria.capter1_6;

import org.junit.Test;

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
