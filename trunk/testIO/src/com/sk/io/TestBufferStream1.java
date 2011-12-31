package com.sk.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestBufferStream1 {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("d:\\HomeActivity.java");
			BufferedInputStream bis = new BufferedInputStream(fis);
			int c = 0;
			System.out.print((char)bis.read());
			System.out.print((char)bis.read());
			bis.mark(100);
			for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
				System.out.print((char)c + " ");
			}
			System.out.println();
			bis.reset();
			for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
				System.out.print((char)c + " ");
			}
			bis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
		}
	}
}
