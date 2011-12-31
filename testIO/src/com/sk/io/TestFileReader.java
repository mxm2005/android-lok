package com.sk.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestFileReader {
	/**
	 * 按字符读   可以读中文
	 * @param args
	 */
	public static void main(String[] args) {
		FileReader fr = null;
		int c = 0;
		try {
			fr = new FileReader("d:\\HomeActivity.java");
			int ln = 0;
			while ((c = fr.read()) != -1) {
//				char ch = (char) fr.read();
				System.out.print((char)c);
//				if (++ln >= 100) {
//					System.out.println();
//					ln = 0;
//				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("文件读取错误");
		}
	}
}
