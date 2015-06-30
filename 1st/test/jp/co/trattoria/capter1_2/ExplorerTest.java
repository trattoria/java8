package jp.co.trattoria.capter1_2;

import java.io.File;

import org.junit.Test;

public class ExplorerTest {

	@Test
	public void testgetSubDirectoryRambda1() {
		File[] subs = new Explorer().getSubDirectoryRambda1("./");

		for(File f: subs){
			System.out.println(f.toString());
		}
	}

	@Test
	public void testgetSubDirectoryRambda2() {
		File[] subs = new Explorer().getSubDirectoryRambda2("./");

		for(File f: subs){
			System.out.println(f.toString());
		}
	}

	@Test
	public void testSubDirectoryMethodReference() {
		File[] subs = new Explorer().getSubDirectoryMethodReference("./");

		for(File f: subs){
			System.out.println(f.toString());
		}
	}

}
