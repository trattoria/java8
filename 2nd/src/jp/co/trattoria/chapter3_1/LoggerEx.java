package jp.co.trattoria.chapter3_1;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author takano
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/function/Supplier.html
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/function/BooleanSupplier.html
 * @see http://www.ne.jp/asahi/hishidama/home/tech/java/functionalinterface.html#h_Supplier
 * @see http://struts.wasureppoi.com/util/04_javalog.html
 */
public class LoggerEx {

	private final Logger logger;

	public LoggerEx(Logger logger) {
	    this.logger = logger;
	    setLogLevel(Level.ALL);
	}

	/**
	 * 評価する条件をセットする
	 * @param level
	 */
	public void setLogLevel(Level level) {
	    logger.setLevel(level);
	}

	/**
	 *
	 * @param level		ログレベル
	 * @param condition	ログ実行の評価結果
	 * @param message	ログ出力するメッセージ
	 */
	public void logIf(Level level, BooleanSupplier condition, Supplier<String> message) {
	    if (logger.isLoggable(level)) {
	        if (condition.getAsBoolean()) {
	        	System.out.println("hogehoge" + message.get());
	            logger.log(level, message.get());
	        }
	    }
	}

	public static void main(String[] args) {

		int[] a = new int[11];

		LoggerEx log = new LoggerEx(Logger.getLogger(LoggerEx.class.getName()));

		a[10] = 1;
		int i = 10;

		// 正常ケース
		log.setLogLevel(Level.INFO);
		log.logIf(Level.INFO, () -> i == 10, () -> "a[10] = " + a[10]);

		// ログレベルにより、出力が抑制される
		log.setLogLevel(Level.OFF);
		log.logIf(Level.INFO, () -> i == 10, () -> "a[10] = " + a[10]);

		// 条件により、出力が抑制される
		log.setLogLevel(Level.INFO);
		log.logIf(Level.INFO, () -> i == 0, () -> "a[10] = " + a[10]);

	}
}
