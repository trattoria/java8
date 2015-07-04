package jp.co.trattoria.capter1_9;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;


/**
 * Q：forEachIfメソッドはどのような場面で活用できるでしょうか。
 * A：Collection2は新規コードである。通常は具象クラス側で実装すればよいのであって、
 * わざわざdefault(インタフェース側)で関数を定義する必要はないと考える。
 * P.18参照。
 *
 * 抽象クラスではなくインタフェースで実装を提供することで、具象クラスに継承の余地を残しておくことはできる。
 * P.19参照
 *
 * @param <T>
 */
public interface Colledction2<T> extends Collection<T> {

    default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        forEach(
            (T t) -> {
                if (filter.test(t))
                    action.accept(t);
            }
        );
    }
}
