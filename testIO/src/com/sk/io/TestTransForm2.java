package com.sk.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestTransForm2 {
	/**
	 * 阻塞式的方法，等待键盘的输入
	 * @param args
	 */
	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);//in
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		try {
			s = br.readLine();
			while (s != null) {
				if (s.equalsIgnoreCase("exit"))
					break;
				System.out.println(s.toUpperCase());
				s = br.readLine();//然后再读下一行
			}
			br.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
