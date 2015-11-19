package ch9_10;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabeledPointTest {

	@Test
	public void test() {
		int result;

		LabeledPoint p1 = new LabeledPoint("a", 1, 1);
		LabeledPoint p2 = new LabeledPoint("b", 1, 1);
		LabeledPoint p3 = new LabeledPoint("a", 2, 1);
		LabeledPoint p4 = new LabeledPoint("b", 1, 2);

		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());

		result = p1.compareTo(p1);
		System.out.println(result);
		assertEquals(result, 0);

		result = p1.compareTo(p2);
		System.out.println(result);
		assertTrue(result < 0);

		result = p2.compareTo(p1);
		System.out.println(result);
		assertTrue(result > 0);

		result = p1.compareTo(p3);
		System.out.println(result);
		assertTrue(result < 0);

		result = p1.compareTo(p4);
		System.out.println(result);
		assertTrue(result < 0);
	}

}
