package com.sk.socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(8888);
			while (true) {
				Socket s1 = s.accept();
				OutputStream os = s1.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF("hello," + s1.getInetAddress() + "port#" + s1.getPort() + " bye-bye!!");
				dos.close();
				s1.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("程序运行出错");
		}
	}
}
