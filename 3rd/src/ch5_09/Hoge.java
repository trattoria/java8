package ch5_09;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Q：(UTCとの時差に)1時間未満の情報(分)が含まれるすべてのタイムゾーンを見つけなさい。
 *
 */
public class Hoge {

	public static String format(Duration duration) {
		if (!duration.isNegative()) {
			return String.format("+%d:%02d", duration.toHours(), duration.toMinutes() % 60);

		} else {
			duration = duration.negated();
			return String.format("-%d:%02d", duration.toHours(), duration.toMinutes() % 60);
		}
	}

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime utcNow = ZonedDateTime.of(now, Clock.systemUTC().getZone());

		ZoneId.getAvailableZoneIds().stream() // タイムゾーン一覧をストリーム化
				.map(ZoneId::of) // ゾーンIDに変換
				.map(zone -> ZonedDateTime.of(now, zone)) // ゾーンIDの日付時間
				.filter(time -> Duration.between(time, utcNow).toMinutes() % 60 != 0) // 分の端数チェック
				.peek(time -> System.out.print(format(Duration.between(time, utcNow)) + ", "))
				.map(ZonedDateTime::getZone) // ゾーンIDに戻す
				.forEach(System.out::println); // タイムゾーン文字列の出力
	}

}
