package ch9_07;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * <Pre>
 * Q：ウェブページの内容を読み込んで、ファイルに保存するプログラムを作成しなさい。
 * URL.openStreamとFiles.copyを使用しなさい。
 * </Pre>
 */
public class Hoge {

	public static void main(String[] args) throws MalformedURLException {

		URL url = new URL("http://www.google.co.jp/");

		try (InputStream openStream = url.openStream();) {

			Path dest = Paths.get("src/ch9_07/out.txt");
			Files.copy(openStream, dest, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			System.out.println("URLを確認してください。");

		}
	}

}
