#!/usr/bin/jjs
/**
 * Q：一連のシェルコマンドを受け取り、1つのコマンドの出力を次のコマンドの入力として接続し、
 * 最後の出力を返すJavaScriptのpipe関数を書きなさい。
 *
 * A：RuntimeException がでて実行できません・・・。
Exception in thread "main" java.lang.RuntimeException: java.io.IOException: Cannot run program "ls" (in directory "\cygdrive\g\java\2nd\java8\4th\src\ch7_06"): CreateProcess error=267, ▒f▒B▒▒▒N▒g▒▒▒▒▒▒▒▒▒▒▒ł▒▒B
        at jdk.nashorn.internal.runtime.ScriptRuntime.apply(ScriptRuntime.java:391)
        at jdk.nashorn.tools.Shell.apply(Shell.java:394)

 */
var pipe = function(x) {
	l = arguments.length;
	print(l)
	for(var i=0 ; i<l ; i++){
		print('exec :' + arguments[i]);
		$EXEC(arguments[i], $OUT)
	}
}

$EXEC("ls -l");
pipe('find .', 'grep -v class', 'sort')



/**
 * @see http://docs.oracle.com/javase/jp/8/docs/technotes/guides/scripting/nashorn/shell.html
 * @see http://qiita.com/YusukeHirao/items/03364b0e82975a03dec6
 * @see http://qiita.com/yuku_t/items/1b8ce6bba133a7eaeb23
 */
