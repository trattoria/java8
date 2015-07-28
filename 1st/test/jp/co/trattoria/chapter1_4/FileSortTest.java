package jp.co.trattoria.chapter1_4;

import java.io.File;

import org.junit.Test;

import jp.co.trattoria.chapter1_4.FileSort;

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
