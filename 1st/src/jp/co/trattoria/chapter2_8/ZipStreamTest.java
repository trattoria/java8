package jp.co.trattoria.chapter2_8;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ZipStreamTest {

	@Test
	public void 有限_有限ストリームの結合() {
		List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
		List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);
		ZipStreamEx.zip(list1.stream(), list2.stream()).limit(10).forEach(System.out::println);
	}
	@Test
	public void 有限_無限ストリームの結合() {
		fail("まだ実装されていません");
	}
	@Test
	public void 無限_無限ストリームの結合() {
		fail("まだ実装されていません");
	}
}
