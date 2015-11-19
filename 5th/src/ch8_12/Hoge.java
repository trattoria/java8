package ch8_12;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <Pre>
 * Q：TestCaseアノテーションを定義し、そのアノテーションを持つクラスをロードして、
 * アノテーションがつけられたメソッドを呼び出し、メソッドが期待した値を生成するかを
 * 検査するプログラムを実装しなさい。
 * パラメータと戻り値は整数だと想定しなさい。
 *
 * A：ロードして・・・とあるので、実行時にアノテーション情報を取得する必要がある。
 * RetentionPolicy.RUNTIMEを付けることで、実行する際にもJavaVMにそのアノテーションの情報が読み込まれる。
 * リフレクションを用いてその情報を取得することができる。
 * </Pre>
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
		final Map<Method, TestCase[]> annotationMap = new HashMap<>();

		final Method[] methods = (Hoge.class).getMethods();

		Stream.of(methods).forEachOrdered(m -> {
			final TestCase[] annotations = m.getAnnotationsByType(TestCase.class);
			// System.out.println(m.getName() + ":" + annotations.length);
			if (annotations.length == 0) {
				return;
			}
			annotationMap.put(m, annotations);
		});

		annotationMap.forEach((method, annotations) -> {
			System.out.println();
			System.out.println(method);

			Stream.of(annotations).forEach(item -> {
				final int params = Integer.valueOf(item.params());
				final long expected = Long.valueOf(item.expected());
				try {
					long result = (long) method.invoke(null, params);
					System.out.printf("params=%4d,expected=%4d,result=%4d%n", params, expected, result);

				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
	}
}
