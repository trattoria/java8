#!/usr/bin/jjs
/**
 * Q：次の呼び出しを行いなさい。
 * A：以下のアウトプットが得られた。
 *
jjs> var b = new java.math.BigInteger('12345678790987654321')
jjs> b
12345678790987655000

 *
 * Q：b.mod(java.math.BigInteger.TEN)の値は何ですか。
 * A：以下のアウトプットが得られた。
 *
jjs> b.mod(java.math.BigInteger.TEN)
1
 *
 * Q：bはなぜ奇妙に表示されるのですか。
 * A：
 *
 */
var b = new java.math.BigInteger('12345678790987654321')
print(b)
print(b.mod(java.math.BigInteger.TEN))

/**
 *
12345678790987655000
1
 *
 *
 *
 * BigInteger(String val)
 * BigIntegerの10進String表現をBigIntegerに変換します。
 * @see http://docs.oracle.com/javase/jp/8/api/java/math/BigInteger.html#BigInteger-java.lang.String-
 */
