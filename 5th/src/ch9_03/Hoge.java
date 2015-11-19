package ch9_03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <Pre>
 * Q：複数例外をキャッチするcatch節でキャッチした例外を再度スローする場合に、
 * その理由が書かれているメソッドのthrows宣言には、例外の型をどのように宣言しますか。
 *
 * </Pre>
 */
public class Hoge {

	private Logger logger;

	/**
	 * A：共通の型であるIOExceptionを宣言する。
	 *
	 * @throws IOException
	 *             FileNotFoundExceptionとUnknownHostExceptionを含みます。
	 */
	public void process() throws IOException {

		String mode = "FileNotFound";

		try {
			switch (mode) {
			case "FileNotFound":
				throw new FileNotFoundException();

			default:
				throw new UnknownHostException();
			}

		} catch (FileNotFoundException | UnknownHostException ex) {
			logger.log(Level.SEVERE, "...", ex);
			throw ex;
		}
	}

	/**
	 * A：例外を羅列する。
	 *
	 * @throws FileNotFoundException
	 *             ほげほげ
	 * @throws UnknownHostException
	 *             ふげふげ
	 */
	public void process(int dummy) throws FileNotFoundException, UnknownHostException {

		String mode = "FileNotFound";

		try {
			switch (mode) {
			case "FileNotFound":
				throw new FileNotFoundException();

			default:
				throw new UnknownHostException();
			}

		} catch (FileNotFoundException | UnknownHostException ex) {
			logger.log(Level.SEVERE, "...", ex);
			throw ex;
		}
	}

	public static void main(String[] args) {

	}

}
