package ch3_23;

import java.util.function.Function;

public class Pair<T> {
	public final T first;
	public final T second;

	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public <U> Pair<U> map(Function<T, U> f) {
		return new Pair<U>(f.apply(first), f.apply(second));
	}

	public static void main(String[] args) {

		Pair<String> p = new Pair<String>("Taro", "Hanako").map(s -> s.toUpperCase());
		System.out.println(p.first + ":" + p.second);
	}

}
