package jp.co.trattoria.capter1_1;

import java.util.Arrays;
import java.util.Random;


/**
 *
 * @author pikanya
 *
 */
public class DisplayThreadID {

	public static void sort(){

		Integer[] integerArray = new Integer[10];

		Random rnd = new Random();
		for (int i=0;i < integerArray.length ; i++ ){
			integerArray[i] = rnd.nextInt(100) + 1;
		}

		Arrays.sort(integerArray, (Integer first,
				Integer second) -> {
					System.out.println(Thread.currentThread().getName());
					return Integer.compare(first, second);
					});

		for(Integer i: integerArray){
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		DisplayThreadID.sort();
	}

}
