package com.sk.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestSocketClient {
	public static void main(String[] args) {
		InputStream is = null;
		OutputStream os = null;
		try {
			Socket socket = new Socket("localhost", 5888);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			DataInputStream dis = new DataInputStream(is);
			dos.writeUTF("hey");
			String s = null;
			if ((s = dis.readUTF()) != null) {
				System.out.println(s);
			}
			dos.close();
			dis.close();
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
