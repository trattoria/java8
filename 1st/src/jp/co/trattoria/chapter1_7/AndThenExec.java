package jp.co.trattoria.chapter1_7;

public class AndThenExec {

	/**
	 * firstを実行した後、secondを実行するRunnableを返す。
	 * @param first		最初に実行するスレッド
	 * @param second	次に実行するスレッド
	 * @return			firstを実行した後、secondを実行するRunnableオブジェクト
	 */
	public static Runnable andThen(Runnable first, Runnable second){
		return ()->{
			first.run();
			second.run();
		};
	}

	public static void main(String[] args) {

		Runnable runner = AndThenExec.andThen(
			()->{ System.out.println("1st."); },
			()->{ System.out.println("2nd."); }
		);

		new Thread(runner).start();
	}
}
