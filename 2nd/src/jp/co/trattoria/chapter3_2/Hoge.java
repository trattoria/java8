package jp.co.trattoria.chapter3_2;

import java.util.concurrent.locks.ReentrantLock;

public class Hoge {

	public static void withLock(ReentrantLock lock, Runnable runner) {
		lock.lock();
		try {
			runner.run();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

		ReentrantLock lock = new ReentrantLock();
		Hoge.withLock(lock, ()->{ System.out.println("example"); });
	}

}
