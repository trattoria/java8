package ch8_16;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * <Pre>
 * Q：市、州、郵便番号を含む行を解析するために名前付きキャプチャグループを用いた正規表現を使用しなさい。
 *
 * A：入力は、カンマ区切りの住所録とします。
 * </Pre>
 */
public class Hoge {

	// P.203。名前付きキャプチャグループ。
	private final static String REGEX = "(?<city>[\\p{L}]+),(?<state>[A-Z]{2}),(?<zip>[0-9]{5}(|-([0-9]{4})))";

	public static void main(String[] args) {
		final Path path = new File("src/ch8_16/address.csv").toPath();

		final Pattern pattern = Pattern.compile(REGEX);

		try (final Stream<String> lines = Files.lines(path)) {
			lines.forEachOrdered(line -> {

				final Matcher matcher = pattern.matcher(line);
				if (!matcher.matches()) {
					System.out.println("unmatch code :" + line);
					return;
				}
				System.out.printf("City     : %s%n", matcher.group("city"));
				System.out.printf("State    : %s%n", matcher.group("state"));
				System.out.printf("Zip Code : %s%n", matcher.group("zip"));
				System.out.println("");
			});

		} catch (IOException e) {
			throw new UncheckedIOException(e);

		}
	}
}
