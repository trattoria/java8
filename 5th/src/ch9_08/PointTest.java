package ch9_08;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void test() {

		System.out.println("テスト１");
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 0);
		int result = p1.compareTo(p2);
		System.out.println("結果：" + result);
		assertEquals(0, result);

		System.out.println("テスト２");
		p1 = new Point(Integer.MAX_VALUE, 0);
		p2 = new Point(Integer.MIN_VALUE, 0);
		result = p1.compareTo(p2);
		System.out.println("結果：" + result);
		assertEquals(1, result);

		System.out.println("テスト３");
		p1 = new Point(Integer.MIN_VALUE, Integer.MAX_VALUE);
		p2 = new Point(Integer.MAX_VALUE, Integer.MIN_VALUE);
		result = p1.compareTo(p2);
		System.out.println("結果：" + result);
		assertEquals(-1, result);

		System.out.println("テスト４");
		p1 = new Point(0, Integer.MAX_VALUE);
		p2 = new Point(0, Integer.MIN_VALUE);
		result = p1.compareTo(p2);
		System.out.println("結果：" + result);
		assertEquals(1, result);

		System.out.println("テスト５");
		p1 = new Point(0, Integer.MIN_VALUE);
		p2 = new Point(0, Integer.MAX_VALUE);
		result = p1.compareTo(p2);
		System.out.println("結果：" + result);
		assertEquals(-1, result);
	}
}
