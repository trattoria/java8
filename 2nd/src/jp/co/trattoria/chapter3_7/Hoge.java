package jp.co.trattoria.chapter3_7;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hoge {

	public static Comparator<String> comparator(boolean nomal, // 正順
			boolean ignoreCase, // 大文字・小文字を区別しない
			boolean excludeSpace) // 空白を除外
	{
		return (t, u) -> {
			// 正順 or 逆順
			int sign = nomal ? 1 : -1;
			// 大文字・小文字を区別 or 区別しない
			if (ignoreCase) {
				t = t.toLowerCase();
				u = u.toLowerCase();
			}
			// 空白を含める or 除外する
			if (excludeSpace) {
				t = t.replaceAll("\\s", "");
				u = u.replaceAll("\\s", "");
			}
			// 比較
			return sign * t.compareTo(u);
		};
	}

	public static void main(String[] args) {

		List<String> list = Arrays.asList("a", " bcd", "ef", "Ghi", "jk");

		Collections.sort(list, Hoge.comparator(true, true, true)); // ラムダ式
		System.out.println(list);

		Collections.sort(list, Hoge.comparator(true, true, false)); // ラムダ式
		System.out.println(list);

		Collections.sort(list, Hoge.comparator(true, false, true)); // ラムダ式
		System.out.println(list);
	}

}
