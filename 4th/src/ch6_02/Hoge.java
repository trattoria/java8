package ch6_02;

/**
 * Q：増加する ID 列を生成するためにLongAdderは役に立ちますか？
 *
 * A：役に立たない。LongAddrはincrementメソッドは値を返さない。
 * 値を得るにはsumメソッドを呼ぶ必要があるが、P.141のコードではsubmitメソッドincrementメソッドの直後でsumメソッドを呼んでも、
 * 並行更新は組み込まれない可能性がある。
 *
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/atomic/
 *      LongAdder.html#sum--
 *      「現在の合計を返します。返される値は原子的スナップショットではありません。並行更新がない場合の呼出しでは正確な結果が返されますが、
 *      合計の計算中に発生した並行更新は組み込まれない可能性があります。」
 *
 * @see P.141、142
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/atomic/
 *      LongAdder.html
 */
public class Hoge {

}
