package ch9_02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * <Pre>
 *Q：練習問題１を改善して、in.close()やout.close()によりスローされる例外を抑制された例外として、
 *元々の例外へ追加するようにしなさい。
 *
 *A：抑制された例外は、ThrowableクラスのaddSuppressed()で追加する。
 *取り出すにはThrowableクラスのgetSuppressed()を使う。
 * </Pre>
 */
public class Hoge {

	static IOException temp;

	public static void main(String[] args) {
		try {
			throwException();

		} catch (Exception e) {
			System.out.println("一つ目の例外:" + e.getMessage());

			Throwable[] ary = e.getSuppressed();
			System.out.println("抑制された例外の数:" + ary.length);

			Arrays.stream(e.getSuppressed()).peek(System.out::println)
					.forEach(t -> System.out.println("抑制された例外:" + t.getMessage()));

			System.out.println();
			System.out.println("StackTrace:");
			e.printStackTrace();
		}
	}

	public synchronized static void throwException() throws IOException {

		String inFile = "src/ch9_02/war-and-peace.txt";
		String outFile = "src/ch9_02/out.txt";

		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Paths.get(inFile));
			out = new PrintWriter(outFile);

			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}

			// テスト用の例外発生
			throw new IOException("tryブロック内の例外");

		} catch (IOException e) {
			temp = e;
			throw e;

		} finally {
			/**
			 * <Pre>
			 * &#64;see http://docs.oracle.com/javase/jp/8/api/java/util/Scanner.html#close--
			 * &#64;see http://docs.oracle.com/javase/jp/8/api/java/io/PrintWriter.html#close--
			 * </Pre>
			 */
			if (Objects.nonNull(in)) {
				in.close();
				// テスト用の例外発生と、抑制された例外としての登録。
				temp.addSuppressed(new IOException("in.close()の例外"));

			}
			if (Objects.nonNull(out)) {
				out.close();
				// テスト用の例外発生と、抑制された例外としての登録。
				temp.addSuppressed(new IOException("out.close()の例外"));
			}
		}
	}

}
