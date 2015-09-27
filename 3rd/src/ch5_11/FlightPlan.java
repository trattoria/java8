package ch5_11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Q：帰りの便は、フランクフルトを14時5分に出発し、ロサンジェルスに16時40分に到着します。 飛行時間は何時間何分ですか。
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

	public FlightPlan(String fPlace, LocalDateTime fTime, String tPlace, LocalDateTime tTime) {

		this.fromPlace = fPlace;
		this.fromTime = ZonedDateTime.of(fTime, ZoneId.of(fPlace, placeMap));
		this.toPlace = tPlace;
		this.toTime = ZonedDateTime.of(tTime, ZoneId.of(tPlace, placeMap));
	}

	/**
	 * 飛行時間の取得
	 */
	public Duration getFlightTime() {
		return Duration.between(fromTime, toTime);
	}

	public static void main(String[] args) {
		FlightPlan plan = new FlightPlan("ロサンジェルス", LocalDateTime.of(2015, 9, 28, 14, 5), "フランクフルト",
				LocalDateTime.of(2015, 9, 28, 16, 40));
		System.out.println(plan.getFlightTime());
	}
}
