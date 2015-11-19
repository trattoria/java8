package ch9_05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <Pre>
 * Q：ファイルからすべての文字を読み込み、逆順に書き出すプログラムを作成しなさい。
 * Files.readAllBytesとFiles.writeを使用しなさい。
 * </Pre>
 */
public class Hoge {

	public static void reverse() throws IOException {

		Path src = Paths.get("src/ch9_05/war-and-peace.txt");
		byte[] bytes = Files.readAllBytes(src);

		List<Byte> list = asList(bytes);
		Collections.reverse(list);

		Path dest = Paths.get("src/ch9_05/out.txt");
		Files.write(dest, toArray(list));
	}

	private static List<Byte> asList(byte[] bytes) {

		final List<Byte> list = new ArrayList<Byte>();
		for (byte b : bytes) {
			list.add(b);
		}
		return list;
	}

	private static byte[] toArray(List<Byte> byteObjs) {

		byte[] array = new byte[byteObjs.size()];
		for (int i = 0; i < byteObjs.size(); i++) {
			array[i] = byteObjs.get(i);
		}
		return array;
	}

	public static void main(String[] args) {
		try {
			Hoge.reverse();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
