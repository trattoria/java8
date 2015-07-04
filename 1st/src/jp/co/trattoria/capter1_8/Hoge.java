package jp.co.trattoria.capter1_8;

import java.util.ArrayList;
import java.util.List;

/**
 * Q：正当なコードでしょうか？
 * A：以下の点から正当なコードと推測。
 * ・変数name自体は、値が変更されない(P.15参照)
 * ・コンパイルエラーにならない
 * ・期待値通り、各ラムダ式は異なる値をキャプチャする
 *
 * Q：従来のforループを使用するとどうなるでしょうか？
 * A：変数iがfinalでないとコンパイルエラーになる。変数iが変更されるprintlnの引数も変わってしまうため不正
 */
public class Hoge {

	public static void main(String[] args) {

		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<Runnable>();
		for (String name : names) {
		    runners.add(() -> System.out.println(name));
		}

		for (Runnable r: runners){
			r.run();
		}

		runners = new ArrayList<Runnable>();
		for (int i = 0; i < names.length; i++){
			// iがfinalでないとコンパイルエラーになる
//		    runners.add(() -> System.out.println(names[i]));
		}
	}
}
