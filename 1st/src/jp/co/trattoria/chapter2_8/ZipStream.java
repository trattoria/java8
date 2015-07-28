package jp.co.trattoria.chapter2_8;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 *
 *
 */
public class ZipStream {

	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {

		Spliterator<T> spliterator = new Spliterator<T>() {
			private Spliterator<T> even = first.spliterator();
			private Spliterator<T> odd = second.spliterator();
			private boolean isEven = true;
			private boolean hasNext = true;

			public int characteristics() {
				int ch = even.characteristics() & odd.characteristics();
				ch &= (Spliterator.IMMUTABLE | Spliterator.NONNULL | Spliterator.SIZED);
				return ch | Spliterator.ORDERED;
			}
			public long estimateSize() {
				if (even.hasCharacteristics(SIZED) && odd.hasCharacteristics(SIZED)) {
					if (even.estimateSize() <= odd.estimateSize()) {
						return 2 * even.estimateSize();
						} else {
							return 2 * odd.estimateSize() + 1;
			        }
			    } else {
			        return Long.MAX_VALUE;
			    }
			}
			public boolean tryAdvance(Consumer<? super T> action) {
			    if (!hasNext) {
			        return false;
			    }
			    hasNext = isEven ? even.tryAdvance(action) : odd.tryAdvance(action);
			    isEven = !isEven;
			    return hasNext;
			}
			public Spliterator<T> trySplit() {
				return null;
			}
		};
		return StreamSupport.stream(spliterator, false);
	}

	public static void main(String[] args) {


	}

}
