package ch5_12;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Q：異なるタイムゾーンにある約束を読み込んで、ローカル時刻で1時間前に約束があることをユーザーに通知するようにしなさい。
 *
 */
public class Appointment implements Comparable<Appointment> {

	public final String title; // 予定の名称
	public final ZonedDateTime time; // 約束の時間

	public Appointment(String title, ZonedDateTime time) {
		this.title = title;
		this.time = time;
	}

	@Override
	public int compareTo(Appointment o) {
		return this.time.compareTo(o.time);
	}

	public static void main(String[] args) {

		List<Appointment> appointments = new LinkedList<Appointment>();
		appointments.add(new Appointment("テレビ会議１",
				ZonedDateTime.of(LocalDateTime.of(2015, 9, 27, 23, 1), ZoneId.of("Asia/Tokyo"))));
		appointments.add(new Appointment("テレビ会議２",
				ZonedDateTime.of(LocalDateTime.of(2015, 9, 28, 9, 30), ZoneId.of("America/Los_Angeles"))));
		appointments.add(
				new Appointment("テレビ会議３", ZonedDateTime.of(LocalDateTime.of(2015, 9, 28, 9, 30), ZoneId.of("CET"))));
		appointments.add(new Appointment("テレビ会議４",
				ZonedDateTime.of(LocalDateTime.of(2015, 9, 27, 23, 1), ZoneId.of("Asia/Tokyo"))));

		// 約束の集まりを時間でソートする。
		Collections.sort(appointments);

		// 1秒毎に約束の有無を確認して、約束があれば通知する。
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(() -> {
			ZonedDateTime nowPlusH1 = ZonedDateTime.now().plusHours(1);

			Iterator<Appointment> itr = appointments.iterator();
			while (itr.hasNext()) {
				Appointment appo = itr.next();
				if (appo.time.isAfter(nowPlusH1)) {
					return;
				}
				System.out.println(appo.title + ": " + appo.time); // 通知
				itr.remove();
			}

		} , 0, 1, TimeUnit.SECONDS);

		try {
			service.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
