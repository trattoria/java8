package ch8_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Q：パスワード保護されたウェブページの内容を取得するプログラムを書きなさい。
 *
 */
public class Hoge {

	public static void main(String[] args) throws MalformedURLException {
		final URL url = new URL("");
		final String username = "hogehoge";
		final String password = "fugefuge";

		URLConnection connection;
		try {
			connection = url.openConnection();
			setAuthProperty(connection, username, password);
			connection.connect();

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			reader.lines().forEachOrdered(System.out::println);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private static void setAuthProperty(final URLConnection connection, final String username, final String password) {
		final Base64.Encoder encoder = Base64.getEncoder();
		final String original = username + ":" + password;
		final String encoded = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + encoded);
	}

}
