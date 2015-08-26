package jp.co.trattoria.chapter3_10;

/**
 * Q：次の呼び出しができない理由は・・・
 *
 * A： Color::brighterの型はUnaryOperator<Color>、Color::grayscaleの型もUnaryOperator
 * <Color>。 この段階では矛盾はない。 composeはスーパークラスのFunctionインタフェースのdefaultメソッドで実装されている。
 * この戻り値の型はFunction<Color, Color>である。 つまり、UnaryOperator<Color>とFunction<Color,
 * Color>の型チェックでエラーとなっていると考える。
 *
 *
 *
 * Q：関数合成に関しては・・・
 *
 * A：？？？
 *
 *
 */
public class Hoge {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
