package ch9_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

/**
 * <Pre>
 * Q：ProcessBuilderクラスを使用して、ユーザーのホームディレクトリのすべてのサブディレクトリ内の
 * すべてのファイルからクレジットカード番号を探すために grep -rを呼び出すプログラムを作成しなさい。
 * ファイル内で見つけた番号をを収集しなさい。
 *
 *
 * VISAカード…１６桁
 * MasterCard…１６桁
 * JCBカード…１６桁
 * アメリカン・エキスプレス…１５桁
 * ダイナースクラブ…１４桁
 * </Pre>
 */
public class CollectionCardNumbers {

	public static void main(String[] args) {

		final String homeDir = System.getProperty("user.home");

		final String regex = "\\d{14,16}";

		// final ProcessBuilder pb = new ProcessBuilder("grep", "-r", regex,
		// homeDir);
		/**
		 * Windows環境なので、findコマンドで代用。
		 */
		final ProcessBuilder pb = new ProcessBuilder("find", regex, homeDir);
		Process process;
		try {
			process = pb.start();
			process.waitFor();

		} catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}

		try (final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			br.lines().forEach(System.out::println);

		} catch (IOException ioe) {
			throw new UncheckedIOException(ioe);
		}
		process.destroy();
	}
}
