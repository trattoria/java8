package jp.co.trattoria.chapter1_4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Fileクラスの配列を受け取って、ディレクトリ優先で名前をソートする。
 */
public class FileSort {

	/**
	 * ディレクトリ、ファイル混在のFileオブジェクト配列を、ディレクトリ優先で、名前の昇順でソートする
	 * @param fileList 未ソートのFileオブジェクト配列
	 * @return 文字列でソートしたFileオブジェクト配列
	 */
	File[] getFileListSort(final File[] fileList){

		List<File> directorys = new ArrayList<File>();
		List<File> files = new ArrayList<File>();

		for(File f: fileList){

			if(f.isDirectory()){
				directorys.add(f);

			}else{
				files.add(f);
			}
		}

		directorys.sort((File a, File b)->{
			return a.compareTo(b);
		});

		files.sort((File a, File b)->{
			return a.compareTo(b);
		});

		List<File> result = new ArrayList<File>();
		result.addAll(directorys);
		result.addAll(files);

		return result.toArray(new File[0]);
	}
}
