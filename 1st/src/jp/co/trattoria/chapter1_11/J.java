package jp.co.trattoria.chapter1_11;

public interface J {

//	void f();

	default void f(){
		System.out.println("J:default");
	};

//	static void f(){
//		System.out.println("J:static");
//	}
}
