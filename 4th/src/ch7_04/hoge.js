#!/usr/bin/jjs
/**
 * Q：文字列から部分文字列を抽出することでリテラルでないJavaScript文字列を生成し、getClassメソッドを呼び出しなさい。
 * 結果はどのクラスになりますか。
 */

var hoge = "Hello, World!".substring(0,5)
print(hoge)
print(hoge.getClass())

var fuge = java.lang.String.class.cast(hoge)
print(fuge)
print(fuge.getClass())


/**
 * Q：文字列から部分文字列を抽出することでリテラルでないJavaScript文字列を生成し、getClassメソッドを呼び出しなさい。
 * 結果はどのクラスになりますか。
 * A：java.lang.Stringになる。
$ ./hoge.js
Hello
class java.lang.String
Hello
class java.lang.String
 */