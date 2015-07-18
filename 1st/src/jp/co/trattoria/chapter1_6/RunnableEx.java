package jp.co.trattoria.capter1_6;

/**
 *
 * @author pikanya
 *
 */
@FunctionalInterface
public interface RunnableEx {

	/**
	 * 例外をスローすることがあるタスクです。結果は返しません。
	 * 実装者は引数のないメソッドrunを定義します。
	 * @throws Exception - 結果を計算できなかった場合
	 */
	public void run() throws Exception;
}
