package ch3_22;

/**
 * Q：CompletableFutureに対するflatMap操作は存在しますか？ 存在するならそれは何ですか？
 *
 * A：設問は、flatMapの引数FunctionのTがCompletableFuture<U>にしたとき、なにかしら得られるかと問うている。
 * 結論として、存在する。 thenCompose()
 *
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/stream/Stream.html#
 *      flatMap-java.util.function.Function-
 * @see http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/
 *      CompletableFuture.html#thenCompose-java.util.function.Function-
 */
public class Hoge {

}
