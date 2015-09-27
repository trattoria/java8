package ch5_02;

import java.time.LocalDate;

/**
 * Q：LocalDate.of(2000, 2, 29)に1年を加算すると何が起きますか？ 4年を加算した場合。 1年を4回加算した場合。
 * A：1年を加算した場合は、うるう年の2月29日は2月28日に丸められる。 4年を加算した場合はうるう年になるが、1年単位で加算すると丸められた日付になる。
 * 2000年、2004年はうるう年。
 */
public class Hoge {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2000, 2, 29);
		date = date.plusYears(1);
		System.out.println(date); // 2001/2/28

		date = LocalDate.of(2000, 2, 29);
		date = date.plusYears(4);
		System.out.println(date); // 2004-02-29

		date = LocalDate.of(2000, 2, 29);
		date = date.plusYears(1);
		date = date.plusYears(1);
		date = date.plusYears(1);
		date = date.plusYears(1);
		System.out.println(date); // 2004-02-28
	}

}
