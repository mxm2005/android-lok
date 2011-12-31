package com.sk.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocketServer {
	public static void main(String[] args) {
		InputStream is = null;
		OutputStream os = null;
		try {
			ServerSocket ss = new ServerSocket(5888);
			Socket socket = ss.accept();
			is = socket.getInputStream();
			os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			DataInputStream dis = new DataInputStream(is);
			String s = null;
			if ((s = dis.readUTF()) != null) {//读的时候用的是inputStream
				System.out.println(s);
				System.out.println("from: " + socket.getInetAddress());
				System.out.println("port: " + socket.getPort());
			}
			dos.writeUTF("hi, hello");//写的时候用的是outputStream
			dis.close();
			dos.close();
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
