package jp.co.trattoria.chapter4_03;

import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Q：デフォルトではない値に設定されたり、xxxProperty()メソッドが最初に呼び出されたときに、要求に応じてプロパティを構築せよ。
 * A：つまり、生成タイミングを遅らせればよいPert2。
 */
public class Greeting {

	// private StringProperty text = new SimpleStringProperty("");
	private String text = "hogehoge";
	private StringProperty property = null;

	synchronized public final StringProperty textProperty() {
		if (property == null) {
			property = new SimpleStringProperty(text);
		}

		return property;
	}

	/**
	 * プロパティのバリューをセットする。
	 *
	 * @param newValue
	 *            文字列
	 *
	 * @throws NullPointerException
	 *             引数がnullの場合
	 */
	synchronized public final void setText(String newValue) {
		Objects.requireNonNull(newValue);

		if (property != null) {
			property.set(newValue);

		} else if (!text.equals(newValue)) {
			property = new SimpleStringProperty(text);

		} else {
			// デフォルト文字列と同じ値がセットされた場合は何もしない。
		}

	}

	synchronized public final String getText() {
		return property != null ? property.get() : text;
	}

	public static void main(String[] args) {
		Greeting obj = new Greeting();

		obj.setText("hogehoge");
		System.out.println(obj.textProperty());

	}
}