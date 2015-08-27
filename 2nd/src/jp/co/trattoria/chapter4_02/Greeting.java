package jp.co.trattoria.chapter4_02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Q：最初は普通のフィールドを使用して、初めてxxxProperty()メソッドが呼び出されたときにだけ
 * プロパティオブジェクトを使用するように、要求に応じてプロパティを構築する方法を示せ。
 *
 * A：つまり、生成タイミングを遅らせればよい。
 */
public class Greeting {

	// private StringProperty text = new SimpleStringProperty("");
	private String text = null;
	private StringProperty property = null;

	public final StringProperty textProperty() {
		if (property == null) {
			property = new SimpleStringProperty(text);
		}

		return property;
	}

	public final void setText(String newValue) {
		if (property != null) {
			property.set(newValue);
		} else {
			text = newValue;
		}

	}

	public final String getText() {
		return property != null ? property.get() : text;
	}

	public static void main(String[] args) {
		Greeting obj = new Greeting();

		obj.setText("hogehoge");
		System.out.println(obj.textProperty());

	}
}