package com.woyi.towerzj_app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Environment;

/**
 * 文件操作类
 */
public class FileUtils {
	private static final int FILESIZE = 4 * 1024;


	public static String getSDPATH() {
		// 得到当前外部存储设备的目录( /SDCARD )
		return Environment.getExternalStorageDirectory()+File.separator;
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 * 
	 * @param path
	 * @param fileName
	 * @param input
	 * @return
	 */
	public static File write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		FileOutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte[] buffer = new byte[FILESIZE];
			int length;
			while ((length = (input.read(buffer))) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 * @return
	 */
	public static File createSDDir(String dirName) {
		File dir = new File(getSDPATH() + dirName);
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

	/**
	 * 在SD卡上创建文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File createSDFile(String fileName) throws IOException {
		File file = new File(getSDPATH() + fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isFileExist(String fileName) {
		File file = new File(getSDPATH() + fileName);
		return file.exists();
	}

	/**
	 * 读取SD卡中文本文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readSDFile(String fileName) {
		StringBuffer sb = new StringBuffer();
		File file = new File(getSDPATH() + fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			int c;
			while ((c = fis.read()) != -1) {
				sb.append((char) c);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
