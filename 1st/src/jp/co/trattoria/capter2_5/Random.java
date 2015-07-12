package jp.co.trattoria.capter2_5;

import java.util.stream.LongStream;

/**
 * 乱数を生成する。
 */
public class Random {

	public static LongStream random(long seed) {
	    final long a = 25214903917L;
	    final long c = 11;
	    final long m = 1 << 48;
	    return LongStream.iterate(seed, x -> (( a * x) + c) % m);
	}

	public static void main(String[] args) {

		long seed = 10;
		new Random();
		LongStream stream = Random.random(seed);
		stream.limit(10).forEach(System.out::println);
	}

}
