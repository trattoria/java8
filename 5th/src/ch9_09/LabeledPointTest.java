package ch9_09;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabeledPointTest {

	@Test
	public void test() {
		boolean result;

		LabeledPoint p1 = new LabeledPoint("hoge", 1, 2);
		LabeledPoint p2 = new LabeledPoint("hoge", 2, 1);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());

		result = p1.equals(p1);
		System.out.println(result);
		assertTrue(result);

		result = p1.equals(p2);
		System.out.println(result);
		assertFalse(result);
	}

}
