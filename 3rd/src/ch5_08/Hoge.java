package ch5_08;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 現在の時刻インスタンスに対してサポートされる全てのタイムゾーンおいて、今日の日付のオフセット（UTC との差）を取得しなさい。
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
				.peek(id -> System.out.print(id + ", ")) // タイムゾーン文字列の出力
				.map(ZoneId::of) // ゾーンIDに変換
				.map(zone -> ZonedDateTime.of(now, zone)) // ゾーンIDの日付時間
				.map(time -> Duration.between(time, utcNow)) // UTC との時差
				.forEach(duration -> System.out.println(Hoge.format(duration)));
	}

}
