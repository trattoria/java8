package jp.co.trattoria.capter2_7;

import java.util.stream.Stream;

public class Hoge {

	/**
	 * Q：isFinite()を作成する。そのうえで良し悪しを判断する。
	 *
	 * A：isFinite()のインタフェース仕様はDoubleの場合、無断台、負の無限大、NaN(Not A Number)でなければtrueを返す。
	 * 本件の場合、無限Streamか否かを判断すればよいだろう。
	 *
	 * メソッドの良し悪しとしては、目的次第でるが、おそらくは良くない。
	 * 無限Streamかどうかの判定に、終端操作であるspliterator()を使用している。
	 * 集団操作であるため、それ以降はこのStreamを利用できなくなってしまう点が良くない。
	 *
	 * @param stream	想定対象のストリーム
	 * * @return		streamが有限あればture、無限であればfalse
	 */
	public static <T> boolean isFinite(Stream<T> stream) {
	    long size = stream.spliterator().getExactSizeIfKnown();
	    return (0 <= size && size < Long.MAX_VALUE);
	}


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
