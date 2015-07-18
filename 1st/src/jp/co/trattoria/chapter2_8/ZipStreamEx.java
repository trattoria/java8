package jp.co.trattoria.capter2_8;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ZipStreamEx {

	public static <T> Stream<T> zip(Stream<T> s1, Stream<T> s2) {

		@SuppressWarnings("unchecked")
		PairIterator itr = new PairIterator(s1.iterator(), s2.iterator(), null);

		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itr, Spliterator.IMMUTABLE | Spliterator.NONNULL), false);
    }

    /** 2つのイテレータから、要素を合成するイテレータ。 */
    private static class PairIterator<T,U,R> implements Iterator<R> {
        private final Iterator<T> i1;
        private final Iterator<T> i2;
        private final BiFunction<T, U, R> mapper;
        public PairIterator(Iterator<T> i1, Iterator<T> i2, BiFunction<T,U,R> mapper) {
            this.i1 = i1;
            this.i2 = i2;
            this.mapper = mapper;
        }
        @Override
        public boolean hasNext() {
            return i1.hasNext() && i2.hasNext();
        }
        @Override public R next() {
            return mapper.apply(i1.next(), (U) i2.next());
        }
    }

    class Pair<T, U> {
        final public T _1;
        final public U _2;
        public Pair(T t, U u) {
            _1 = t;
            _2 = u;
        }
        @Override public boolean equals(Object o1){
            if (o1 instanceof Pair) {
                Pair p = (Pair)o1;
                return _1.equals(p._1) && _2.equals(p._2);
            }
            return false;
        }
        @Override public int hashCode() {
            int hash = 1;
            hash = hash * 31 + _1.hashCode();
            hash = hash * 31 + _2.hashCode();
            return hash;
        }
        @Override public String toString(){
            return "(" + _1 + "," + _2 +")";
        }
    }

	public static void main(String[] args) {

	}

}
