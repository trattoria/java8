package ch8_08;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * <Pre>
 * A：テスト対象にしているgetMoreWork(Queue q)はキューqに文字列を追加するだけのコードです。
 * qはPath型を格納するキューなので型の不一致をおこしますが、Queueの型情報を消しているので
 * getMoreWork()ではチェックされません。
 * 以下の例では、checkedQueue()を使うことで型チェックされるようになります。
 * </Pre >
 */
public class HogeTest {

	@Test
	public void unCheckedSample() {
		Hoge obj = new Hoge();
		Queue<Path> q = new LinkedList<Path>();

		// 挿入タイミングでClassCastExceptionが発生しない。
		obj.getMoreWork(q);
		assertTrue(true);
	}

	@Test(expected = ClassCastException.class)
	public void checkedSample() {
		Queue<Path> q = Collections.checkedQueue(new LinkedList<Path>(), Path.class);
		Hoge obj = new Hoge();

		// 挿入タイミングでチェックされて、ClassCastExceptionが発生する。
		obj.getMoreWork(q);
		fail();
	}
}
