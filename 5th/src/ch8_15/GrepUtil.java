package ch8_15;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * <Pre>
 * Q：Files.linesとPattern.asPredicateを使用して、正規表現に一致するすべての行を表示し、grepユーティリティのように
 * 動作するプログラムを書きなさい。
 *
 * </Pre>
 *
 */
public class GrepUtil {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("input err.");
			System.exit(1);
		}
		String regex = args[0];
		String filePath = args[1];

		System.out.println(regex);
		System.out.println(filePath);

		final Path path = Paths.get(filePath);
		try (final Stream<String> lines = Files.lines(path)) {
			Pattern pattern = Pattern.compile(regex);
			Predicate<String> predicate = pattern.asPredicate();
			lines.filter(l -> predicate.test(l)).forEachOrdered(System.out::println);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
