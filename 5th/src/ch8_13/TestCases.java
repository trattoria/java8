/**
 *
 */
package ch8_13;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCases {
	TestCase[]value();
}