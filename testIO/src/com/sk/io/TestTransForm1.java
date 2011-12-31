package com.sk.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TestTransForm1 {
	/**
	 * 转换流
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:\\HomeActivity.java"));
			osw.write("microsoftibmsunapplehp");//第一次输入
			System.out.println(osw.getEncoding());
			osw.close();
			osw = new OutputStreamWriter(new FileOutputStream("d:\\hello.txt", true), "ISO8859_1");//参数true,在文件原来的基础上往上加
			osw.write("microsoftibmsunapplehp");//第二次输入
			System.out.println(osw.getEncoding());
			osw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
		}
	}
}
