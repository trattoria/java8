package ch3_24;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch3_23.Pair;

/**
 * Q：Pair<T>に対するflatMapメソッドを定義することができますか？
 *
 * A：Pairの要素は2個と固定のため、PairをListのようには利用してflatMapは定義できない。
 * flatMapはStream内の要素を受け取りStreamを返すメソッドである。
 * Collectionの中にネストしたCollectionがある場合になどに使える。 Pairを入れ子にした場合、Pair<Paire<T>>となる。
 */
public class Hoge {

	public static void main(String[] args) {
		Pair<Pair<Integer>> hoge = new Pair<Pair<Integer>>(new Pair<Integer>(1, 1), new Pair<Integer>(1, 1));

		// A：streamを以下のように用意すればできる。
		List<Integer> result1 = Stream.of(hoge.first, hoge.second).flatMap((p1) -> {
			return Stream.of(p1.first, p1.second);
		}).collect(Collectors.toList());

		for (Integer i : result1) {
			System.out.println(i);
		}
	}
}
