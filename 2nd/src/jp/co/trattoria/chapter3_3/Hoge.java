package jp.co.trattoria.chapter3_3;

/**
 * Q：なぜ、アサーションは、ライブラリの機能として提供されなかったのでしょう。
 *
 * アサーションが有効か無効かを意味するアサーション状態は、クラスローダーが保持するテーブル上のフラグで管理され、
 * ロードしたクラスのフラグが立っている場合は、クラス初期化時にアサーションが有効になる。
 * つまり、アサーションが不要な場合(リリースなど)は、「評価しない」状態にできる。
 *
 * ライブラリで提供した場合は、メソッド呼び出しであり、呼び出した時点で条件式を評価することになる。
 * おそらく、これを避けたかったと推測する。
 *
 *
 * Q：Java8ではライブラリの機能として実装することができますか。
 * できる。
 * 遅延実行により、必要な場合だけメソッド呼び出しする状態を実現できる。
 *
 */
public class Hoge {

	public static void main(String[] args) {
	}

}
