package ch8_13;

import ch8_12.TestCase;

/**
 * Q：練習問題12を繰り返しなさい。ただし、ソースレベルのアノテーションプロセッサを構築しなさい。
 *
 */
public class Hoge {

	// P.198
	@TestCase(params = "4", expected = "24")
	@TestCase(params = "0", expected = "1")
	public static long factorial1(int n) {
		return n <= 1 ? 1 : n * factorial1(n - 1);
	}

	@TestCase(params = "4", expected = "24")
	@TestCase(params = "0", expected = "1")
	public static long factorial2(int n) {
		return n <= 1 ? 1 : n * factorial2(n - 1);
	}

	public static void main(String[] args) {

	}

}
