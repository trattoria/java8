package jp.co.trattoria.capter1_4;

import java.io.File;

import org.junit.Test;

public class FileSortTest {

	@Test
	public void testGetFileListSort() {


		File[] fileList = new File("/").listFiles();

		File[] sortedFiles = new FileSort().getFileListSort(fileList);

		for(File f: sortedFiles){
			System.out.println(f);
		}
	}

}
