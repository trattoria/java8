package ch6_07;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <Pre>
 * Q：ConcurrentHashMap<String, Long>内で、最大値(出現回数)を持つキーを見つけなさい。
 *
 * &#64;see http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/ConcurrentHashMap.html#computeIfAbsent-K-java.util.function.Function-
 * </Pre>
 */
public class WordCounter {

	public static void getMaxCountWord(String path) throws InterruptedException, IOException {

		List<String> words;
		String contents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		words = Arrays.asList(contents.split("[\\P{L}]+"));

		// "a"や"the"を除くために3文字以上を対象とする。
		ConcurrentHashMap<String, Long> map = (ConcurrentHashMap<String, Long>) words.stream()
				.filter(w -> w.length() > 3).map(String::toLowerCase)
				.collect(Collectors.toConcurrentMap(Function.identity(), w -> 1L, Long::sum));

		Map.Entry<String, Long> maxCount = map.reduceEntries(1, (e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2);
		System.out.println("最出の単語 " + maxCount.getKey() + ":" + maxCount.getValue() + "個");
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		WordCounter.getMaxCountWord("src/ch6_05/war-and-peace.txt");
	}
}
