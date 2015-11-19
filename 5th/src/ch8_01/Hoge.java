package ch8_01;

/**
 * Q：int値と符号なし操作を使用して、0から2の32乗-1までの間の数値の加算、減産、除算、比較を行うプログラムを書きなさい。
 * divideUnsigned()とremainderUnsigned()が必要な理由を示しなさい。
 *
 * <Pre>
 * A：divideUnsigned()が必要な理由
 * Javaでn%2を求めたとき、nが負であると結果が負になるため(P.188参照)。
 * divideUnsigned()とremainderUnsigned()であれば、結果を自然数(>0)で得られる。
 * </Pre>
 */
public class Hoge {

	static public int add(int a, int b) {
		return Math.addExact(a, b);
	}

	static public int subtract(int a, int b) {
		return Math.subtractExact(a, b);
	}

	static public int divideSinged(int a, int b) {
		return a / b;
	}

	static public int divideUnsigned(int a, int b) {
		return Integer.divideUnsigned(a, b);
	}

	static public int remainderSinged(int a, int b) {
		return a % b;
	}

	static public int remainderUnsigned(int a, int b) {
		return Integer.remainderUnsigned(a, b);

	}

	static public int compareUnsigned(int a, int b) {
		return Integer.compareUnsigned(a, b);

	}

	public static void main(String[] args) {

		// ユニットテストを参照。
	}

}
