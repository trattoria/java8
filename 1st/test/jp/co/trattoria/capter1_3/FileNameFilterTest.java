package jp.co.trattoria.capter1_3;

import org.junit.Test;

public class FileNameFilterTest {

	@Test
	public void testGetSubDirectory() {
		String[] files = new FileNameFilter().getFileList("/", "jpg");

		for(String s: files){
			System.out.println(s);
		}
	}

}
