package ch5_03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

/**
 * Q：Predicate<LocalDate>を受け取りTemporalAdjusterを返すnextメソッドを実装しなさい。
 *
 * @see http://docs.oracle.com/javase/jp/8/api/java/time/temporal/
 *      TemporalAdjuster.html
 */
public class Hoge {

	/**
	 * 指定された条件式を満たす「今日の日付の後」の日付。「今日の日付の後」はこのメソッド内で実現すること。
	 *
	 * @param predicate
	 *            条件式
	 * @return 条件に合致したTemporalAdjusterのオブジェクト
	 * @throws java.lang.IllegalArgumentException
	 *             本日より1000日以内に該当の日にちが存在しない
	 */
	public static TemporalAdjuster next(Predicate<LocalDate> predicate) {

		return t -> {
			LocalDate w = (LocalDate) t;
			int max = 1000; // 1000日以内
			int i = 0;

			for (i = 0; i < max; i++) {
				w = w.plusDays(1); // 今日より後にあって・・・の実装。
				if (predicate.test(w)) {
					break;
				}
			}

			if (i >= max) {
				throw new IllegalArgumentException("本日より" + max + "日以内に該当の日にちは存在しません。");
			}
			// LocalDateはTemporalAdjusterインタフェースの実装である。
			return w;
		};
	}

	public static void main(String[] args) {

		LocalDate today = LocalDate.now();

		// http://docs.oracle.com/javase/jp/8/api/java/time/LocalDate.html#with-java.time.temporal.TemporalAdjuster-
		LocalDate weekday = today.with(Hoge.next(w -> w.getDayOfWeek().getValue() < 6));
		System.out.println(weekday);
	}

}
