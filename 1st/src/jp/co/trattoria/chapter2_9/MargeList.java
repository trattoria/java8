package jp.co.trattoria.chapter2_9;

import java.util.ArrayList;
import java.util.List;

/**
 * Q：Stream<ArrayList<T>>内の全ての要素を、1つのArrayList<T>へまとめる
 * 3つの形式のreduceを用いる方法を提示する
 *
 * @see http://d.hatena.ne.jp/nowokay/20130504
 */
public class MargeList {

	List<ArrayList<String>> target;

	MargeList(	List<ArrayList<String>> val){
		target = val;
	}

	void marge1(){

		ArrayList<String> list = this.target.stream()
			.reduce(
					new ArrayList<String>(),
					(a, b) -> { a.addAll(b); return a; });

		list.forEach(System.out::println);
	}


	void marge2(){
		ArrayList<String> marged = this.target.stream()
			.reduce(
					(a, b) -> { a.addAll(b); return a; }
					)
			.orElse(new ArrayList<String>());

		marged.forEach(System.out::println);
	}

	void marge3(){
		ArrayList<String> marged = this.target.stream()
			.reduce(
					new ArrayList<String>(),
					(a, b) -> { a.addAll(b); return a; },
					(a, c) -> { a.addAll(c); return a; }
					);

		marged.forEach(System.out::println);
	}

	public static void main(String[] args) {

		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		ArrayList<String> list3 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list2.add("4");
		list2.add("5");
		list2.add("6");
		list3.add("7");
		list3.add("8");
		list3.add("9");

		List<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
		lists.add(list1);
		lists.add(list2);
		lists.add(list3);

		MargeList obj = new MargeList(lists);

		System.out.println("test1:");
		obj.marge1();

		System.out.println("test3:");
		obj.marge3();

		// 実行順序で、結果が変わる？
		System.out.println("test2:");
		obj.marge2();
	}

}
