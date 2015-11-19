package ch8_02;

/**
 * <Pre>
 * Q：
 *
 * A：-2147483648(-2の32乗)。反転すると2147483648だが、intの上限は2147483647。
 *
 *---(実行結果)-------
 *1
 *2147483647
 *java.lang.ArithmeticException: integer overflow
 *
 * </Pre>
 */
public class Hoge {

	public static void main(String[] args) {
		System.out.println(Math.negateExact(0));
		System.out.println(Math.negateExact(-1));
		System.out.println(Math.negateExact(-2147483647));
		try {
			System.out.println(Math.negateExact(-2147483648));

		} catch (ArithmeticException e) {
			System.out.println(e);

		}

	}

}
