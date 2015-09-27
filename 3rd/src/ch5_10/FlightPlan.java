package ch5_10;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Q：ロサンジェルスからフランクフルト行きの便は、ローカル時刻の3時50分に出発し、10時間50分のフライトです。何時に到着しますか？
 * 到着時刻は、現地のローカル時間で出力することにする。
 */
public class FlightPlan {

	/** 地名とゾーンIDの対応 */
	private static Map<String, String> placeMap;

	static {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ロサンジェルス", "America/Los_Angeles"); // -7:00
		map.put("フランクフルト", "CET"); // +2:00, 中央ヨーロッパ
		placeMap = Collections.unmodifiableMap(map);
	}

	public final String fromPlace; // 出発地
	public final ZonedDateTime fromTime; // 出発時刻
	public final String toPlace; // 到着地
	public final ZonedDateTime toTime; // 到着時刻

	public FlightPlan(String fPlace, LocalDateTime fTime, String tPlace, Duration flightTime) {

		this.fromPlace = fPlace;
		// ローカルの日付と時間からZonedDateTimeのインスタンスを取得する。
		this.fromTime = ZonedDateTime.of(fTime, ZoneId.of(fPlace, placeMap));
		this.toPlace = tPlace;

		// 到着時間の計算。withZoneSameInstant()は、インスタントを保持したまま、別のタイムゾーンを使ってこの日付/時間のコピーを返す。
		this.toTime = this.fromTime.plus(flightTime).withZoneSameInstant(ZoneId.of(tPlace, placeMap));
	}

	public static void main(String[] args) {
		FlightPlan plan = new FlightPlan("ロサンジェルス", LocalDateTime.of(2015, 9, 28, 3, 5), "フランクフルト",
				Duration.ofHours(10).plusMinutes(50));
		System.out.println(plan.toTime);
	}
}
