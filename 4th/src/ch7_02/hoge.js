#!/usr/bin/jjs
/**
 * Q：jjsを実行し、ストリームライブラリを使用して、次の問題に対する解放をインタラクティブに求めなさい。
 * 問題：あるファイルに含まれている長い単語を重複なしですべてソートしなさい。
 */

var text = readFully("./war-and-peace.txt")
var words = java.util.Arrays.asList<java.lang.String>(text.split("[\\P{L}]+"))
//var words = java.util.Arrays.asList<java.lang.String>(text.split(" "))

print(words)
print(words.length)
print(words[0])
print(words[1])


/*
ConcurrentHashMap<String, Long> map = (ConcurrentHashMap<String, Long>) words.stream()
		.filter(w -> w.length() > 3).map(String::toLowerCase)
		.collect(Collectors.toConcurrentMap(Function.identity(), w -> 1L, Long::sum));

Map.Entry<String, Long> maxCount = map.reduceEntries(1, (e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2);
System.out.println("最出の単語 " + maxCount.getKey() + ":" + maxCount.getValue() + "個");
*/

