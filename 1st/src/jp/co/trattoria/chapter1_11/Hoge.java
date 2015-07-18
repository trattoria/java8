package jp.co.trattoria.capter1_11;


/**
 * Q：・・・すべての組み合わせで何が起きるでしょうか。
 * A：ルールはP.19 参照。組み合わせは以下の通り。
 * I			J
 * 抽象			抽象		Overrideが必要。I or Jの解決は不要。
 * 抽象			デフォルト	Overrideが必要。
 * 抽象			静的		Overrideが必要。
 * デフォルト	デフォルト	Overrideが必要して、I or Jの解決が必要。
 * デフォルト	静的		Overrideは不要。
 * 静的			静的		f()は未定義で呼び出せない
 *
 * スーパークラスとインタフェースでまったくシグニチャが同じ場合は、
 * スパークラスが優先される。
 *
 *
 *
 */
public class Hoge implements I, J {

//	@Override
//	public void f() {
//		System.out.println("Hoge: Override");
//
//	}
	public static void main(String[] args) {
		Hoge obj = new Hoge();
		obj.f();
	}
}
