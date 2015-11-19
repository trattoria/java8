package ch8_06;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Q：Comparatorインタフェースのメソッドだけを使用して、Point2Dに対する全順序なコンパレータを定義しなさい。
 *
 */
public class Hoge {

	final static Comparator<Point2D> comparatorPoint2D = Comparator.comparingDouble(Point2D::getX)
			.thenComparingDouble(Point2D::getY);

	final static Comparator<Rectangle2D> comparatorRectangle2D = Comparator.comparingDouble(Rectangle2D::getX)
			.thenComparingDouble(Rectangle2D::getY).thenComparingDouble(Rectangle2D::getWidth)
			.thenComparingDouble(Rectangle2D::getHeight);

	public static void main(String[] args) {

		List<Point2D> list1 = new ArrayList<Point2D>();
		list1.add(new Point());
		list1.add(new Point(0, 0));
		list1.add(new Point(0, 1));
		list1.add(new Point(1, 0));
		list1.add(new Point(1, 1));

		System.out.println("Point2D:" + Hoge.comparatorPoint2D.compare(list1.get(0), list1.get(1)));
		System.out.println("Point2D:" + Hoge.comparatorPoint2D.compare(list1.get(1), list1.get(2)));
		System.out.println("Point2D:" + Hoge.comparatorPoint2D.compare(list1.get(2), list1.get(3)));
		System.out.println("Point2D:" + Hoge.comparatorPoint2D.compare(list1.get(3), list1.get(4)));
		System.out.println("Point2D:" + Hoge.comparatorPoint2D.compare(list1.get(4), list1.get(0)));

		List<Rectangle2D> list2 = new ArrayList<Rectangle2D>();
		list2.add(new Rectangle());
		list2.add(new Rectangle(0, 0, 0, 0));
		list2.add(new Rectangle(0, 0, 0, 1));
		list2.add(new Rectangle(0, 0, 1, 0));
		list2.add(new Rectangle(0, 0, 1, 1));
		list2.add(new Rectangle(0, 1, 0, 0));
		list2.add(new Rectangle(0, 1, 0, 1));
		list2.add(new Rectangle(0, 1, 1, 0));
		list2.add(new Rectangle(0, 1, 1, 1));
		list2.add(new Rectangle(1, 0, 0, 0));

		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(0), list2.get(1)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(1), list2.get(2)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(2), list2.get(3)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(3), list2.get(4)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(4), list2.get(5)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(5), list2.get(6)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(6), list2.get(7)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(7), list2.get(8)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(8), list2.get(9)));
		System.out.println("Rectangle2D:" + Hoge.comparatorRectangle2D.compare(list2.get(9), list2.get(0)));
	}

}
