package ch5_01;

import java.time.LocalDate;

/**
 * Q：plusDaysメソッドを使用しないで、プログラマーの日を計算しなさい。
 * A：プログラマの日は、元旦から256日目である。256日の加算をplusDaysを使わずに行うということ。
 * 具体的には1月1日から256日目にあたる9月13日（閏年の場合は9月12日）。
 */
public class ProgrammersDay {

	public static LocalDate getProgrammersDay() {
		return getProgrammersDay(LocalDate.now().getYear());
	}

	public static LocalDate getProgrammersDay(int y) {

		return LocalDate.ofYearDay(y, 256);
	}

	public static void main(String[] args) {
		System.out.println(ProgrammersDay.getProgrammersDay().toString());

		// うるう年
		System.out.println(ProgrammersDay.getProgrammersDay(2008).toString());

	}

}
