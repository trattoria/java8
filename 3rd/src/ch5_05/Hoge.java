package ch5_05;

import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.temporal.ChronoUnit;

/**
 * Q：今までに、あなたが生きてきた日数を表示するプログラムを書きなさい。
 *
 * A：和暦を表すクラス（JapaneseDate）なんのがあった。
 *
 * @see http://docs.oracle.com/javase/jp/8/api/java/time/chrono/JapaneseDate.
 *      html
 * @see http://www.atmarkit.co.jp/ait/articles/1412/16/news041_5.html
 */
public class Hoge {

	public static void main(String[] args) {
		JapaneseDate birthday = JapaneseDate.of(JapaneseEra.SHOWA, 50, 1, 1); // 日付はダミー。
		long n = birthday.until(LocalDate.now(), ChronoUnit.DAYS);
		System.out.printf("X Dayから今日まで %1$,3d 日です", n + 1);
	}

}
