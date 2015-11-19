package ch9_10;

import java.util.Objects;

/**
 * <Pre>
 * Q：連取問題9のLabeledPointクラスにcompareToメソッドを実装しなさい。
 * </Pre>
 *
 */
public class LabeledPoint {

	private String label;
	private int x;
	private int y;

	public LabeledPoint(String l, int x, int y) {
		this.label = l;
		this.x = x;
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		// instanceof演算子の演算結果は、左辺がnullの場合はfalseになる。
		if (!(o instanceof LabeledPoint)) {
			return false;
		}
		LabeledPoint other = (LabeledPoint) o;
		return Objects.equals(this.label, other.getLabel()) && Objects.equals(this.x, other.getX())
				&& Objects.equals(this.y, other.getY());

	}

	/**
	 * equals()メソッドとhashCode()メソッドは、2つのオブジェクトがequals()メソッドで等しいと判定された場合、
	 * 両者のハッシュコードは等しくなければならない。
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.label, this.x, this.y);
	}

	public int compareTo(LabeledPoint other) {
		Objects.requireNonNull(other);

		int diff = Objects.toString(this.label, "").compareTo(Objects.toString(other.label, ""));
		if (diff != 0) {
			return diff;
		}

		diff = Integer.compare(this.x, other.x);
		if (diff != 0) {
			return diff;
		}

		return Integer.compare(this.y, other.y);
	}

	public static void main(String[] args) {

		LabeledPoint p1 = new LabeledPoint("hoge", 1, 2);
		LabeledPoint p2 = new LabeledPoint("hoge", 2, 1);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p1.equals(p1));
		System.out.println(p1.equals(p2));
	}
}
