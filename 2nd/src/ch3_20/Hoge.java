package ch3_20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Q：staticメソッドである<T, U> List<U> map(List<T>, Function<T, U>)を提供しなさい。
 *
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/stream/Collectors.html
 */
public class Hoge {

	static <T, U> List<U> map(List<T> list, Function<T, U> mapper) {
		return list.stream().map(mapper).collect(Collectors.toList());

	}

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("bc");
		list.add("def");
		list.add("ghij");
		list.add("klnmo");
		list.add("pqrstu");

		List<Integer> length = Hoge.map(list, s -> s.length());
		length.forEach(System.out::println);
	}

}
