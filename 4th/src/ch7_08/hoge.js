#!/usr/bin/jjs
/**
 * Q：すべての環境変数の値を表示すrうスクリプトを書きなさい。
 * A：
 */

var env = $ENV
var map = java.util.HashMap<java.lang.String, java.lang.String>.class.cast(env)
var size = map.size()
print("$key : " + size)

var keys = map.keySet()

/**
 *
for (i=0 ; i<size ; i++){
	print("$key : " + $ENV{$key})
}
 */
