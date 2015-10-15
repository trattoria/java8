#!/usr/bin/jjs
/**
 * Q：ユーザの年齢を取得しtえ、年齢に1を加えて、Next year, ・・・と表示するnextYear.jsスクリプトを書きなさい。
 */


var year = ''
if (!(typeof arguments[0] === "undefined")) {
	year = arguments[0]
	print("arguments: " + year)

} else if(!(typeof $ENV.AGE ===  "undefined")){
	year = $ENV.AGE
	print("env: " + year)
}else{
//	year = require('fs').readFileSync('/dev/stdin', 'utf8')
	year = readLine('User year: ')
	print("stdin" + year)
}
year++
print("Next year, you will be ... " + year)
