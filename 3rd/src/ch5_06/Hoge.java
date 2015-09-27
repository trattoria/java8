package ch5_06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

/**
 * Q：20世紀の全ての13日の金曜日を列挙せよ
 *
 */
public class Hoge {

	public static void main(String[] args) {

		// 20世紀の初めと終わり
		YearMonth startMonth = YearMonth.of(1901, Month.JANUARY);
		YearMonth lastMonth = YearMonth.of(2000, Month.DECEMBER);

		YearMonth month = startMonth;
		while (month.isBefore(lastMonth) || month.equals(lastMonth)) {

			// 毎月13日のLocalDateを取得して金曜日なら出力。
			LocalDate day = month.atDay(13);
			if (day.getDayOfWeek() == DayOfWeek.FRIDAY) {
				System.out.println(day);
			}
			month = month.plusMonths(1);
		}
	}

}
