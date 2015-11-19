package ch8_10;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <Pre>
 * Q：JDKに含まれるsrc.zipファイルを展開しなさい。Files.walkを使用して、予約語である
 * transientとvolatileを含むJavaのファイルをすべて見つけなさい。
 * </Pre>
 */
public class Hoge {

	public static void main(String[] args) throws IOException {

		final String jreHome = System.getProperty("java.home");
		String serchPath = jreHome.substring(0, jreHome.lastIndexOf("\\"));

		try (Stream<Path> topdir = Files.walk(Paths.get(serchPath))) {
			topdir.filter(path -> (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS))).filter(file -> {
				try {
					byte[] bytes = Files.readAllBytes(file);
					final String content = new String(bytes, StandardCharsets.UTF_8);
					return content.contains("transient") || content.contains("volatile");

				} catch (IOException e) {
					throw new UncheckedIOException(e);

				}
			}).sorted().forEach(System.out::println);

		} catch (IOException e) {
			throw new UncheckedIOException(e);

		}

	}

}
