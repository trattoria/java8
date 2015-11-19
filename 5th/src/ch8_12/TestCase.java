package ch8_12;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * </Pre>
 * RetentionPolicy.RUNTIMEを付けることで、実行する際にもJavaVMにそのアノテーションの情報が読み込まれる。
 * リフレクションを用いてその情報を取得することが出来る。
 * </Pre>
 */
@Repeatable(TestCases.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCase {
	String params();

	String expected();
}