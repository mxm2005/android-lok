package com.sk.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

public class TestPrintStream3 {
	public static void main(String[] args) {
		String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			FileWriter fw = new FileWriter("d:\\b.txt", true);
			PrintWriter log = new PrintWriter(fw);
			while ((s = br.readLine()) != null) {
				if (s.equalsIgnoreCase("exit"))
					break;
				System.out.println(s.toUpperCase());
				log.println("-------");
				log.println(s.toUpperCase());
				log.flush();
			}
			log.println("===" + new Date() + "===");
			log.flush();
			log.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
