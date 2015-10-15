#!/usr/bin/jjs
/**
 * Q：ProcessBuilderクラスを使用して、その問題を解決しなさい。
 *
 * A：RuntimeException がでて実行できません・・・。
 * Exception in thread "main" java.lang.RuntimeException: java.io.IOException: Cannot run program "find .": CreateProcess error=2, ▒w▒肳▒ꂽ▒t▒@▒C▒▒▒▒▒▒▒▒▒▒܂▒▒▒B
 */



var pipe = function(x) {
	l = arguments.length;
	print(l)
	var stdout = new java.util.ArrayList()
	for(var i=0 ; i<l ; i++){
		print('exec :' + arguments[i]);
		var pb = new java.lang.ProcessBuilder(arguments[i], stdout)
		var process = pb.start()

		var is = process.getInputStream()
		var br = new java.io.BufferedReader(new java.io.InputStreamReader(is))
		for (;;) {
			var line = br.readLine()
			if (line == null) break
			print(line)
		}
//		stdout = $OUT
		process.waitFor()
	}
}

pipe('find .', 'grep -v class', 'sort')



/**
var dfdPipe = function (event, props) {
	var dfd = jQuery.Deferred();
	mixpanel.track(event, props, function () {
		dfd.resolve();
	});
	cond ? dfd.resolve() : dfd.reject();
	return dfd.promise();
};

dfdPipe(true)
	.done(function () { console.log('Resolved!'); })
	.fail(function () { console.log('Rejected!'); });



 * @see http://docs.oracle.com/javase/jp/8/docs/technotes/guides/scripting/nashorn/shell.html
 * @see http://qiita.com/YusukeHirao/items/03364b0e82975a03dec6
 * @see http://qiita.com/yuku_t/items/1b8ce6bba133a7eaeb23
 */
