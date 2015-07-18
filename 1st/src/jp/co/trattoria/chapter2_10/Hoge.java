package jp.co.trattoria.capter2_10;

import java.util.stream.Stream;

public class Hoge {

	public static void main(String[] args) {

		Stream<Double> stream = Stream.of(1.0, 2.0, 3.0, 4.0, 5.0);

		double[] result = stream.reduce(
				new double[3],
				(a, b) -> {
					a[0] += b.doubleValue();
					a[1] += 1.0;
					a[2] = a[0] / a[1];
					return a;
					},
				(a, c) -> {
					a[0] += c[0];
					a[1] += c[1];
					a[2] = a[0] / a[1];
					return a;
					}
			);

		System.out.println("result = " + result[2]);
	}

}
