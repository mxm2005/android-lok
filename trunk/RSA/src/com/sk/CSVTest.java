package com.sk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.csvreader.CsvReader;

public class CSVTest {
	public static void main(String[] args) {
		try {
			File file = new File("e://sms_20121010143330.csv");
			InputStream is = new FileInputStream(file);
//			System.out.println(inputStream2String(is));
			CsvReader reader = new CsvReader("e://sms_20121010143330.csv");
			System.out.println(reader.getColumnCount());
			String[] str = reader.getValues();
			for (int i = 0; i < str.length; i++) {
				System.out.println(str[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("---");
		}
	}

	static String inputStream2String(InputStream is) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
