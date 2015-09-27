package ch5_07;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Q1：カレンダーのイベント（例えば、指定された日付の午前10時から午後11時まで）に適した、時刻のインターバルを表す TimeInterval
 * クラスを実装しなさい。 Q2：二つのインターバルが重なっているかどうかを検査するメソッド提供しなさい。
 *
 *
 */
public class TimeInterval {

	// LocalDateとLocalDateTimeは同じように日付であるが、LocalDateTimeは秒まで含む。
	public final LocalDateTime from; // 開始時刻
	public final LocalDateTime to; // 終了時刻

	// 同じ日のイベント。日をまたぐことはできない。
	public TimeInterval(LocalDate date, LocalTime from, LocalTime to) {
		this(date.atTime(from), date.atTime(to));
	}

	public TimeInterval(LocalDateTime from, LocalDateTime to) {

		if (from.isAfter(to)) {
			throw new IllegalArgumentException("不正な開始/終了時刻");
		}
		this.from = from;
		this.to = to;
	}

	// 二つのインターバルが重なっているかどうかを検査するメソッド。
	public boolean isOverlap(TimeInterval other) {
		LocalDateTime start = from.isAfter(other.from) ? from : other.from;
		LocalDateTime end = to.isBefore(other.to) ? to : other.to;
		return start.isBefore(end);
	}

	public static void main(String[] args) {
		TimeInterval val1 = new TimeInterval(LocalDate.of(2015, 9, 28), LocalTime.of(10, 00), LocalTime.of(11, 00));
		TimeInterval val2 = new TimeInterval(LocalDate.of(2015, 9, 28), LocalTime.of(11, 00), LocalTime.of(12, 00));
		TimeInterval val3 = new TimeInterval(LocalDate.of(2015, 9, 28), LocalTime.of(10, 30), LocalTime.of(11, 30));

		System.out.println(val1.isOverlap(val2));
		System.out.println(val1.isOverlap(val3));

	}

}
