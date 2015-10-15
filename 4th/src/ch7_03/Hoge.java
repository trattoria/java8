package ch7_03;

import java.math.BigInteger;

public class Hoge {

	public static void main(String[] args) {

		BigInteger b = new java.math.BigInteger("12345678790987654321");
		System.out.println(b);
		System.out.println(b.mod(java.math.BigInteger.TEN));
	}

}
