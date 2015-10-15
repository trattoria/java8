#!/usr/bin/jjs
/**
 * Q：172ページの・・・ファクトリとなるJavaScript関数を書きなさい。
 * A：
 */

var getFactory = function() {
	return new (Java.extend(java.util.ArrayList)) {
		add: function(x) {
			print('Adding ' + x);
			return Java.super(arr).add(x)
		}
	}
}


/*
var arr = new (Java.extend(java.util.ArrayList)) {
	add: function(x) {
			print('Adding ' + x);
			return Java.super(arr).add(x)
	}
}
*/
var arr = getFactory.getFactory()
arr.add('Fred')
print(arr[0])

