#!/usr/bin/jjs

print(arguments);
print(arguments[0]);
print(arguments[1]);
print(arguments[2]);

print($ARG);
print($ARG[0]);
print($ARG[1]);
print($ARG[2]);

var list = new java.util.ArrayList()
list.add("hoge")
list.add("fuge")
print(list.get(0))
print(list.get(1))

//ドット記法バージョン。
var input1 = java.time.ZonedDateTime
//クラス情報。
print(input1)
//メソッド呼び出し。
print(input1.now())

// Java.type関数バージョン。
var input2 = Java.type('java.time.ZonedDateTime')
print(input2.now())



/**
 * Q：ZonedDateTimeクラスなど、試してみたいJavaAPIを選びなさい。そのうえで、オブジェクトの生成、メソッドの呼び出し、戻り値の表示など、
 * jjsを使った実験を行いなさい。また、Javaでテストプログラムを書くよりは簡単かどうかを検証しなさい。
 * A：以下の場所にjjsの実行ファイルがある。
 * \pleiades-e4.5-ultimate-jre_20150624\pleiades\eclipse\jre\jre\bin\jjs.exe
 * 出力は以下のようになる。
$ ./hoge.js -- arg1 arg2 arg3
arg1,arg2,arg3
arg1
arg2
arg3
arg1,arg2,arg3
arg1
arg2
arg3
hoge
fuge
[JavaClass java.time.ZonedDateTime]
2015-10-13T22:14:48.517+09:00[Asia/Tokyo]
2015-10-13T22:14:48.520+09:00[Asia/Tokyo]


Javaでテストプログラムを書くよりは簡単かどうか・・・、場合によるのでは？
少なくとも、タブ保管機能も履歴機能もない環境でプログラミングするのは辛い。


 */



/**
var input = new java.util.Scanner( new java.net.URL('http://google.co.jp').openStream())
input.useDelimiter('$')
var contents = input.next()
contents
 */


