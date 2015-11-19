package ch8_03;

import java.io.IOException;

/**
 * <Pre>
 * Q：gcdのアルゴリズムを(手段A)%、(手段B)floorMode、(手段C)数学的な(負ではない)余りを生成するrem関数で実装しなさい。
 * これらの3つの方法のどれが負の値に対して最も簡単ですか？
 *
 * A：
 * 手段Aは見た目にアルゴリズムが分かり易い。
 *
 * 手段BはfloorMod()自体を感覚的にとらえにくいので、成否を判断しづらい。ただし、Javadocに正のモジュラスを
 * 取得する方法が記載されているため、これを(信じて)流用するのであれば難易度は下がる。
 *
 * 手段Cは手段Aの絶対値を得る場所が変わっているだけ。ループごとに正の整数かする分、余計に計算が発生しそう。
 * 最大公約数は、入力が負であっても最終的に絶対値をとって正の整数かするだけなので、
 * 複雑さはどの手段も大して変わらない。
 *
 * 結論として、個人の感触としては手段Aが分かり易い。
 * </Pre>
 */

public class Gcd {
	int first;
	int second;

	Gcd(int a, int b) {

		if (a > b) {
			first = a;
			second = b;
		} else {
			first = b;
			second = a;
		}
	}

	/**
	 *
	 * @param line
	 *            数値の文字列表現。
	 * @return 2個の数値を格納した配列。
	 * @throws IllegalArgumentException
	 *             引数がおかしい。
	 */
	public static int[] parseInt(String input) {

		String[] line = input.split("\\s");

		int[] numArray = new int[line.length];
		for (int i = 0; i < line.length; i++) {
			numArray[i] = Integer.parseInt(line[i]);
		}
		if (numArray.length != 2) {
			throw new IllegalArgumentException("引数の数がおかしい");
		}

		return numArray;
	}

	public int percent() {
		// 入力値を正の整数化したほうが手っ取り早い・・・。
		// int a = Math.abs(first);
		// int b = Math.abs(second);
		int a = first;
		int b = second;

		if (b == 0) {
			return 0;
		}

		int tmp;
		while ((tmp = a % b) != 0) {
			a = b;
			b = tmp;
		}
		return Math.abs(b);
	}

	/**
	 * <Pre>
	 * &#64;see http://docs.oracle.com/javase/jp/8/api/java/lang/Math.html#floorMod-int-int-
	 * </Pre>
	 */
	public int floorMode() {
		int a = first;
		int b = second;

		if (b == 0) {
			return 0;
		}
		int tmp;
		while ((tmp = (Math.floorMod(a, b) + Math.abs(b)) % Math.abs(b)) != 0) {
			a = b;
			b = tmp;
		}
		return b;
	}

	/**
	 * 再帰呼び出しバージョン。再帰呼び出しはすっきりかけるかもしれないが、メモリを使う。
	 */
	public static int FloorMod_Recursion(final int a, final int b) {
		if (b == 0) {
			return a;
		}
		return FloorMod_Recursion(b, (Math.floorMod(a, b) + Math.abs(b)) % Math.abs(b));
	}

	private int remFunc(final int a, final int b) {
		return Math.abs(a % b);
	}

	public int rem() {
		int a = first;
		int b = second;

		if (b == 0) {
			return 0;
		}

		int tmp;
		while ((tmp = remFunc(a, b)) != 0) {
			a = b;
			b = tmp;
		}
		return b;
	}

	public static void main(String args[]) throws IOException {
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		// String line = br.readLine();
		// int[] numArray = Gcd.parseInt(line);

		int[][] numArray = { { 24, 16 }, { -24, 16 }, { 24, -16 }, { -24, -16 } };

		for (int[] array : numArray) {
			Gcd gcd = new Gcd(array[0], array[1]);
			System.out.println("start.");
			System.out.println(gcd.percent());
			System.out.println(gcd.floorMode());
			System.out.println(gcd.rem());
			// おまけ。
			System.out.println(Gcd.FloorMod_Recursion(array[0], array[1]));
			System.out.println("end.");
		}
	}

}
