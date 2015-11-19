package ch8_14;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <Pre>
 * Q：Objects.requireNonNullメソッドの使用方法を示し、そのメソッドにより、
 * もっと役立つエラーメッセージとなる方法を示しなさい。
 * </Pre>
 */
public class Hoge {

	/**
	 * Objects.requireNonNullメソッドの使用方法デモ。簡潔にnullチェックできるところがメリット。
	 */
	private static int demoMethod(final String s) {
		Objects.requireNonNull(s);
		return s.length();
	}

	private static int demoMethod2(final String s) {
		requireNonNullEx(s, () -> "Argument must not be null.");
		return s.length();
	}

	/**
	 * requireNonNullWrapperのラッパーメソッドとして役立つメソッドを提供する。時間情報を付与する。
	 */
	private static <T> T requireNonNullEx(T obj, final Supplier<String> msgSupplier) {
		final String msg = (msgSupplier == null ? "" : msgSupplier.get());
		return Objects.requireNonNull(obj, () -> {
			return "[" + Instant.now() + " -> " + msg + "]";
		});
	}

	public static void main(String[] args) {

		try {
			demoMethod(null);

		} catch (NullPointerException e) {
			System.out.println("Objects.requireNonNullメソッドの使用方法デモ。");
			System.out.println(e.getMessage());
			System.out.println();
		}

		try {
			demoMethod2(null);

		} catch (NullPointerException e) {
			System.out.println("Objects.requireNonNullメソッドについての役立つメソッドのデモ。");
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

}
