package jp.co.trattoria.chapter1_12;

import java.util.AbstractList;

/**
 * Q：安全か？
 * A：安全ではないシナリオ１：
 * 実装側でstreamメソッドを定義している場合は、コンパイルエラーとなる。
 *
 * Q：バイナリ互換について、JARファイルからの古いコードは動作するのでしょうか。
 * A：
 */
public class Hoge extends AbstractList<Integer>{

	@Override
	public Integer get(int index) {

		return null;
	}

	@Override
	public int size() {

		return 0;
	}

	/**
	 * シグニチャが違うので、compileエラーとなる
	 */
    public void stream() {
        System.out.println("hogehoge");
    }

//	  public Stream<Integer> stream() {
//		System.out.println("hogehoge");
//		return null;
//	}

}
