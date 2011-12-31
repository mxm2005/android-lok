package com.sk.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestObjectIO {
	public static void main(String[] args) {
		T t = new T();
		t.k = 8;
		try {
			FileOutputStream fos = new FileOutputStream("d:/c.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(t);
			oos.flush();
			oos.close();
			
			FileInputStream fis = new FileInputStream("d:/b.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			T tReaded = (T) ois.readObject();
			System.out.println(tReaded.i + " " + tReaded.j + " " + tReaded.d + " " + tReaded.k);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

@SuppressWarnings("serial")
class T implements Serializable {
	int i = 10;
	int j = 9;
	double d = 2.3;
	transient int k = 15;//透明的
}
