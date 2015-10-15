package ch6_08;

import java.util.Arrays;
import java.util.Random;

/**
 * Q：みなさんのコンピュータでは、Arrays.parallelSortは、配列がどのくらいの大きさであればArrays.sortより速くなりますか？
 * A：10Kを超えたあたり(11264)からparallelの方が速くなり始めた。
 *
 * <Pre>
 *
要素数 : 8192
Single : 0.005981106秒
Multi  : 0.002387405秒

要素数 : 9216
Single : 0.002917682秒
Multi  : 0.002692715秒

要素数 : 10240
Single : 0.003162527秒
Multi  : 0.003030315秒

要素数 : 11264
Single : 0.003334488秒
Multi  : 0.003148415秒

要素数 : 12288
Single : 0.003451312秒
Multi  : 0.003501003秒

要素数 : 13312
Single : 0.003927744秒
Multi  : 0.003904906秒

要素数 : 14336
Single : 0.004223191秒
Multi  : 0.00428028秒

要素数 : 15360
Single : 0.004715834秒
Multi  : 0.004584183秒

要素数 : 16384
Single : 0.004964345秒
Multi  : 0.004912993秒
 *
 *
 * </Pre>
 *
 */
public class CompareSpeed_SingleVersusMulti {

	String[] bigArray = new String[16 * 1024];
	int arraySize = 8 * 1024; // 測定対象のサイズ
	int count;

	public CompareSpeed_SingleVersusMulti() {
		this(100);
	}

	public CompareSpeed_SingleVersusMulti(int c) {
		this.count = c;

		// 測定対象の作成。
		Random random = new Random();
		for (int i = 0; i < bigArray.length; i++) {
			bigArray[i] = Double.toString(random.nextDouble());
		}
	}

	synchronized double getCountAgerageSingle() {

		String[] array;
		long startTime = 0;
		long totalTime = 0;
		for (int i = 0; i < this.count; i++) {
			array = Arrays.copyOf(bigArray, arraySize);
			startTime = System.nanoTime();
			Arrays.sort(array);
			totalTime += System.nanoTime() - startTime;
		}

		return (totalTime / this.count) / 1E9;
	}

	synchronized double getCountAgerageMalti() {

		String[] array;
		long startTime = 0;
		long totalTime = 0;
		for (int i = 0; i < this.count; i++) {
			array = Arrays.copyOf(bigArray, arraySize);
			startTime = System.nanoTime();
			Arrays.parallelSort(array);
			totalTime += System.nanoTime() - startTime;
		}

		return (totalTime / this.count) / 1E9;
	}

	void exec() {
		for (int size = 8 * 1024; size <= bigArray.length; size += 1024) {
			arraySize = size;

			System.out.println("要素数 : " + size);
			System.out.println("Single : " + getCountAgerageSingle() + "秒");
			System.out.println("Multi  : " + getCountAgerageSingle() + "秒");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new CompareSpeed_SingleVersusMulti().exec();
	}

}
