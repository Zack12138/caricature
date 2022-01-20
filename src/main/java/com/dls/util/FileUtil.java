/**  
 * @Project Name:reptile  
 * @File Name:FileUtil.java  
 * @Package Name:com.dls.reptile.util  
 * @Date:2019年12月22日下午7:15:13  
 * Copyright (c) 2019, dongls All Rights Reserved.  
 *  
*/

package com.dls.util;

import com.dls.constant.ReptileException;

import java.io.*;

/**
 * @Package: com.dls.reptile.util
 * @ClassName: FileUtil
 * @Description: 文件操作类
 * @date: 2019年12月22日 下午7:55:13
 * 
 * @author dongls
 * @version V2.0
 */
public class FileUtil {

	private FileUtil() {
	}

	public static void saveFile(String path, String str) {
		saveFile(path, str, false);
	}

	public static void saveFile(String path, String str, boolean append) {
		FileWriter fileWriter = null;
		try {
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			fileWriter = new FileWriter(path, append);
			fileWriter.write(str, 0, str.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null)
					fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String loadFile(String path) {
		File file = new File(path);
		if (!file.exists() || !file.canRead()) {
			System.out.println();
		}
		return null;

	}


	public static void saveByte(String path, byte[] bytes){
		saveByte(path,bytes,0,bytes.length);
	}
	public static void saveByte(String path, byte[] bytes,int off,int len){
		BufferedOutputStream out = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			out = new BufferedOutputStream(new FileOutputStream(path));
			out.write(bytes, off, len);
		}catch (Exception e){
			throw new ReptileException(e);
		}finally{
			try {
				out.flush();
			} catch (IOException e) {
			}
			try {
				out.close();
			} catch (IOException e) {
			}
		}


	}

}