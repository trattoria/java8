package jp.co.trattoria.chapter1_3;

import org.junit.Test;

import jp.co.trattoria.chapter1_3.FileNameFilter;

public class FileNameFilterTest {

	@Test
	public void testGetSubDirectory() {
		String[] files = new FileNameFilter().getFileList("/", "jpg");

		for(String s: files){
			System.out.println(s);
		}
	}

}
