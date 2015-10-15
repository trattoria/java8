package ch6_09;

import java.util.Arrays;

/**
 * <Pre>
 * Q：フィボナッチ数の計算を並列化するためにArrays.parallelPrefixメソッドを使用することができます。 2×2の行列で配列を埋めなさい。
 * </Pre>
 */
public class Matrix {

	/**
	 * 要素の配列(2×2)。one-basedで扱う。
	 */
	private double[][] a = new double[2 + 1][2 + 1];

	public Matrix() {

	}

	// 要素を指定する場合
	public Matrix(double a11, double a12, double a21, double a22) {
		a[1][1] = a11;
		a[1][2] = a12;
		a[2][1] = a21;
		a[2][2] = a22;
	}

	/**
	 * 要素の取得。
	 *
	 * @param row
	 * @param column
	 * @return
	 */
	public double get(int row, int column) {
		return a[row][column];
	}

	/**
	 * 要素の設定。
	 *
	 * @param row
	 * @param column
	 * @param value
	 */
	public void set(int row, int column, double value) {
		a[row][column] = value;
	}

	/**
	 * 乗算。
	 *
	 * @param other
	 * @return
	 */
	public Matrix multiply(Matrix other) {
		Matrix result = new Matrix();
		for (int row = 1; row <= 2; row++) {
			for (int column = 1; column <= 2; column++) {
				result.a[row][column] = 0;
				for (int i = 1; i <= 2; i++) {
					result.a[row][column] += a[row][i] * other.a[i][column];
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {

		final Matrix f = new Matrix(1, 1, 1, 0);
		Matrix[] array = new Matrix[100];

		// 条件１：parallelSetAllで行列の配列を作成する。
		Arrays.parallelSetAll(array, i -> f);

		// 条件２：parallelPrefixで行列を乗算する。
		Arrays.parallelPrefix(array, Matrix::multiply);

		Arrays.stream(array).forEach(m -> System.out.println(m.get(1, 1)));
	}

}
