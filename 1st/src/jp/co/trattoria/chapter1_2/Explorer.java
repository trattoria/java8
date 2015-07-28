package jp.co.trattoria.chapter1_2;

import java.io.File;

public class Explorer {

	File[] getSubDirectoryRambda1(String path){

		return new File(path).listFiles((f)->{
			return f.isDirectory();
		});
	}

	File[] getSubDirectoryRambda2(String path){

		return new File(path).listFiles(f->f.isDirectory());
	}

	File[] getSubDirectoryMethodReference(String path){

		return new File(path).listFiles(File::isDirectory);
	}
}
