package ch5_04;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Q：ある月のカレンダーを表示するUnixのcalと同じプログラムを書きなさい。
 *
 */
public class Cal {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// 細かなコマンドライン引数のチェックは省略
		if (args.length > 3) {
			// マニュアル表示して終了。マニュアル表示は省略。
			System.exit(1);
		}

		YearMonth yearMonth = YearMonth.of(Integer.parseInt(args[1]), Integer.parseInt(args[0]));
		LocalDate day = yearMonth.atDay(1);
		LocalDate lastDay = yearMonth.atEndOfMonth();

		// 先頭行をインデント
		int n = day.getDayOfWeek().getValue() - 1;
		for (int i = 0; i < n; i++) {
			System.out.print("   ");
		}

		// 日付を右寄せ
		while (day.isBefore(lastDay) || day.isEqual(lastDay)) {
			System.out.printf("%2d", day.getDayOfMonth());
			// 1文字空ける or 改行
			if (day.getDayOfWeek() == DayOfWeek.SUNDAY) {
				System.out.println();
			} else {
				System.out.print(" ");
			}
			day = day.plusDays(1);
		}
	}

}
