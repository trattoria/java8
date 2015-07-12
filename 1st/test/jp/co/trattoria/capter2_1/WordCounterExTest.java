package jp.co.trattoria.capter2_1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class WordCounterExTest {

	/**
	 * 異なる手段でカウントした値と同じになることを評価する。
	 */
	@Test
	public void 正常ケース() {
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get("src\\jp\\co\\trattoria\\capter2_1\\sample.txt")), StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			WordCounterEx.setPreSetting(words, 8);
			WordCounterEx.startCount(100);

			WordCounter counter = new WordCounter();
			long count = 0;
			count = counter.oldCounter(words);
			System.out.println("counter1:" + count);
			System.out.println("counter2:" + WordCounterEx.totalCount);
			assertEquals(WordCounterEx.totalCount.get(), count);

		} catch (IOException e) {
			fail("ファイルが開けません。");
		}
	}
}
