package jp.co.trattoria.capter1_3;

import java.io.File;

/**
 *
 * 解答：
 * エンクロージングスコープからキャプチャされている変数はextention。
 */
public class FileNameFilter {
	String[] getFileList(final String path, final String extention){

		return new File(path).list((File dir, String name)->{

			String[] temp = name.split("\\.");

			if(temp.length < 2){
				return false;

			}else if(temp[temp.length - 1].equals(extention)){
				return true;

			}else{
				return false;
			}
		});
	}
}
