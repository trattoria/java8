package ch8_09;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Q：Scannerを単語、行、整数、あるいは、double値のストリームへ変換するメソッドを書きなさい。
 *
 */
public class ScannerToStream {

	public static Stream<String> getWordStream(Scanner s) {
		Objects.requireNonNull(s);

		final Iterator<String> iter = new Iterator<String>() {
			final Scanner scn = s;

			@Override
			public boolean hasNext() {
				return scn.hasNext();
			}

			@Override
			public String next() {
				return scn.next();
			}
		};
		return StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

	public static Stream<String> getLinesStream(Scanner s) {
		Objects.requireNonNull(s);

		final Iterator<String> iter = new Iterator<String>() {
			final Scanner scn = s;

			@Override
			public boolean hasNext() {
				return scn.hasNextLine();
			}

			@Override
			public String next() {
				return scn.nextLine();
			}
		};
		return StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

	public static Stream<Integer> getIntStream(Scanner s) {
		Objects.requireNonNull(s);

		final Iterator<Integer> iter = new Iterator<Integer>() {
			final Scanner scn = s;

			@Override
			public boolean hasNext() {
				boolean hasNumber = false;

				while (scn.hasNext()) {
					hasNumber = scn.hasNextLong();
					if (hasNumber) {
						return true;
					}
					scn.next();
				}
				return false;
			}

			@Override
			public Integer next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return scn.nextInt();
			}
		};
		return StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);

	}

	public static Stream<Double> getDoubleStream(Scanner s) {
		Objects.requireNonNull(s);

		final Iterator<Double> iter = new Iterator<Double>() {
			final Scanner scn = s;

			@Override
			public boolean hasNext() {
				boolean hasNumber = false;

				while (scn.hasNext()) {
					hasNumber = scn.hasNextDouble();
					if (hasNumber) {
						return true;
					}
					scn.next();
				}

				return false;
			}

			@Override
			public Double next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return scn.nextDouble();
			}

		};
		return StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}

	public static <T> void print(Scanner s, final Function<Scanner, Stream<T>> method) {
		Objects.requireNonNull(s);

		Stream<T> result = method.apply(s);
		result.forEachOrdered(System.out::println);
	}

	public static void main(String[] args) throws IOException {

		Scanner scanner;

		System.out.println("Scanner -> Word Stream.");
		scanner = new Scanner("1 fish 2 fish red fish blue fish");
		ScannerToStream.<String> print(scanner, s -> ScannerToStream.getWordStream(s));
		scanner.close();

		System.out.println("");
		System.out.println("Scanner -> Line Stream.");
		scanner = new Scanner(Paths.get("src/ch8_09/alice_fix.txt"));
		ScannerToStream.<String> print(scanner, s -> ScannerToStream.getLinesStream(s));

		System.out.println("");
		System.out.println("Scanner -> Integer Stream.");
		scanner = new Scanner("1 fish 2 fish red fish blue fish");
		ScannerToStream.<Integer> print(scanner, s -> ScannerToStream.getIntStream(s));
		scanner.close();

		System.out.println("");
		System.out.println("Scanner -> Double Stream.");
		scanner = new Scanner("1 fish 2 fish red fish blue fish");
		ScannerToStream.<Double> print(scanner, s -> ScannerToStream.getDoubleStream(s));
		scanner.close();

	}

}
