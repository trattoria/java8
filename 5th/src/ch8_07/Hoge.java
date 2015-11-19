package ch8_07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Q：reversed を呼び出すことなく、nullsFirst(naturalOrder()).reversed()を表現しなさい。
 *
 *
 */
public class Hoge {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("a", "bcd", null, "ef");

		list.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
		System.out.println(list);

		list.sort(Comparator.nullsFirst(Comparator.reverseOrder()));
		System.out.println(list);

	}
}
