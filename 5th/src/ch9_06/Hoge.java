package ch9_06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * <Pre>
 * Q：ファイルからすべての行を読み込み、逆順に書き出すプログラムを作成しなさい。
 * Files.readAllLinesとFiles.writeを使用しなさい。
 *
 * </Pre>
 *
 */
public class Hoge {

	public static void reverse() throws IOException {

		Path src = Paths.get("src/ch9_06/war-and-peace.txt");
		List<String> strList = Files.readAllLines(src);

		Collections.reverse(strList);

		Path dest = Paths.get("src/ch9_06/out.txt");
		Files.write(dest, strList);
	}

	public static void main(String[] args) {
		try {
			Hoge.reverse();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
