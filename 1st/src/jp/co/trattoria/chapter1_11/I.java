package jp.co.trattoria.chapter1_11;

public interface I {

//	void f();

//	default void f(){
//		System.out.println("I:default");
//	};

	static void f(){
		System.out.println("static");
	}
}
