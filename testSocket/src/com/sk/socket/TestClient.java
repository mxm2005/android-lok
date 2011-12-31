package com.sk.socket;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class TestClient {
	/**
	 * 通过socket对象可以获取通信对方socket的信息
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s1 = new Socket("127.0.0.1", 8888);
			InputStream is = s1.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			System.out.println(dis.readUTF());
			dis.close();
			s1.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("程序运行出错!!!");
		}
	}
}
