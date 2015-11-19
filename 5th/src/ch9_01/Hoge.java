package ch9_01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * <Pre>
 * Q：212ページの9.1.1節「try-with-resources分」の最後にあるScannerとPrinterを生成している
 * コードを、try-with-resources分を使用しないで実装しなさい。
 * </Pre>
 */
public class Hoge {

	public static void main(String[] args) {

		String inFile = "src/ch9_01/war-and-peace.txt";
		String outFile = "src/ch9_01/out.txt";

		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Paths.get(inFile));
			out = new PrintWriter(outFile);

			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}

		} catch (IOException e) {
			// 特にリカバリーできることはない。
			e.printStackTrace();

		} finally {
			/**
			 * <Pre>
			 * 練習問題の指摘とは異なり、Scannerクラス、PrintWriterクラス共に close()は
			 * 例外を返さない。Javadocによると2重closeしても影響ないらしい。
			 * &#64;see http://docs.oracle.com/javase/jp/8/api/java/util/Scanner.html#close--
			 * &#64;see http://docs.oracle.com/javase/jp/8/api/java/io/PrintWriter.html#close--
			 * </Pre>
			 */
			if (Objects.nonNull(in)) {
				in.close();
			}
			if (Objects.nonNull(out)) {
				out.close();
			}
		}
	}
}
