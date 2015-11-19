package ch8_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class HogeTest {

	@Test
	public void test加算() {
		System.out.println();
		System.out.println("加算");

		int param[][] = { { 1, 2, 3 }, { 2, 3, 5 } };
		int result;
		for (int[] p : param) {
			System.out.print(p[0] + "+" + p[1] + " : ");
			result = Hoge.add(p[0], p[1]);
			System.out.println("result=" + result + " : " + "expected=" + p[2]);
			assertEquals(p[2], result);
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test加算_桁あふれ() {
		System.out.println();
		System.out.println("加算_桁あふれ");

		int p[] = { (int) (Math.pow(2, 32) - 1), 1 };
		System.out.println(p[0] + "+" + p[1] + " : ");
		Hoge.add(p[0], p[1]);
	}

	@Test
	public void test減算() {
		System.out.println();
		System.out.println("減算");

		int param[][] = { { 3, 2, 1 }, { 4, 3, 1 } };
		int result;
		for (int[] p : param) {
			System.out.print(p[0] + "-" + p[1] + " : ");
			result = Hoge.subtract(p[0], p[1]);
			System.out.println("result=" + result + " : " + "expected=" + p[2]);
			assertEquals(p[2], result);
		}
	}

	@Test(expected = ArithmeticException.class)
	public void test減算_桁落ち() {
		System.out.println();
		System.out.println("減算_桁落ち");

		int p[] = { -2147483648, 1 };
		System.out.println(p[0] + "-" + p[1] + " : ");
		Hoge.subtract(p[0], p[1]);
	}

	@Test
	public void test除算の剰余_返り値は符号あり() {
		System.out.println();
		System.out.println("除算の剰余_返り値は符号あり");
		int param[][] = { { 10, 2, 0 }, { 9, 2, 1 }, { -9, 2, -1 } };
		int result;
		for (int[] p : param) {
			System.out.print(p[0] + "%" + p[1] + " : ");
			result = Hoge.remainderSinged(p[0], p[1]);
			System.out.println("result=" + result + " : " + "expected=" + p[2]);
			assertEquals(p[2], result);
		}
	}

	@Test
	public void test除算の剰余_返り値は符号なし() {
		System.out.println();
		System.out.println("除算の剰余_返り値は符号なし");

		int param[][] = { { 10, 2, 0 }, { 9, 2, 1 }, { -9, 2, 1 } };

		int result;
		for (int[] p : param) {
			System.out.print(p[0] + "%" + p[1] + " : ");
			result = Hoge.remainderUnsigned(p[0], p[1]);
			System.out.println("result=" + result + " : " + "expected=" + p[2]);
			assertEquals(p[2], result);
		}
	}

	@Test
	public void test2つのint値を符号なしとして処理して数値的に比較() {
		System.out.println();
		System.out.println("2つのint値を符号なしとして処理して数値的に比較");

		int param[][] = { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 1, -1 }, { -1, -1, 0 } };

		int result;
		for (int[] p : param) {
			System.out.print("compare:" + p[0] + "、" + p[1] + " : ");
			result = Hoge.compareUnsigned(p[0], p[1]);
			System.out.println("result=" + result + " : " + "expected=" + p[2]);
			assertEquals(p[2], result);
		}
	}
}
