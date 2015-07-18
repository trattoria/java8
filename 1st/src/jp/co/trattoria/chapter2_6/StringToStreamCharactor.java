package jp.co.trattoria.capter2_6;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringToStreamCharactor {

	public static Stream<Character> characterStream(String s) {

		// range()は、第2引数を含まない範囲を対象とする。
	    return IntStream.range(0, s.length()).mapToObj(n -> s.charAt(n));
	}

	public static void main(String[] args) {
		String s = "hogehoge";
		StringToStreamCharactor.characterStream(s).forEach(System.out::println);
	}
}
