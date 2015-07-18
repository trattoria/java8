package jp.co.trattoria.capter2_4;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Q:配列int[]のStream.ofは何になるか？
 * A：Stream<int[]>になる。
 *
 */
public class hoge {

	public static void main(String[] args) {
		int[] values = { 1,4,9,16 };

		Stream<int[]> hoge = Stream.of(values);

		IntStream stream = IntStream.of(values);
		stream.forEach(System.out::println);

	}
}
