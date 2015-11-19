package ch9_08;

/**
 * Q：224ページの9.3.3節「数値型を比較する」のPointクラスのcompareToメソッドを、
 * Integer.compareToを使用せずに実装しなさい。
 */
public class Point {

	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int compareTo(Point other) {
		// longであつかうことでオーバーフロー回避。
		long diff_int = this.x - other.x;
		long diff_long = (long) this.x - other.x;

		System.out.println("x:int:" + diff_int);
		System.out.println("x:long:" + diff_long);

		if (diff_long != 0) {
			return diff_long > 0 ? 1 : -1;
		}

		diff_int = this.y - other.y;
		diff_long = (long) this.y - other.y;
		System.out.println("x:int:" + diff_int);
		System.out.println("x:long:" + diff_long);

		if (diff_long != 0) {
			return diff_long > 0 ? 1 : -1;

		} else {
			return 0;
		}
	}

	public static void main(String[] args) {

		Point p1 = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point p2 = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

		int result = p1.compareTo(p2);
		System.out.println("MAX_VALUE - MIN_VALUEでオーバーフロー");
		System.out.println(result);
	}

}
