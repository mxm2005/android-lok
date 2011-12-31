package com.sk.io;

import java.io.FileWriter;
import java.io.IOException;

public class TestFileWriter {
	public static void main(String[] args) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("d:\\unicode.dat");
			for (int c = 0; c <= 50000; c++) {
				fw.write(c);
			}
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("文件写入错误");
			System.exit(-1);
		}
	}
}
