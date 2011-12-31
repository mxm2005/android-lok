package com.sk.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TestDataStream {
	/**
	 * 数据流
	 * @param args
	 */
	public static void main(String[] args) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//在内存里new一个字节数组，一根管道插到内存字节数组上面了
		DataOutputStream dos = new DataOutputStream(baos);//在ByteArrayOutputStream外面套一层DataOutputStream管道，更加好用，可写8个字节(在内存字节数组里已有8个字节)
		try {
			dos.writeDouble(Math.random());
			dos.writeBoolean(true);//boolean类型在内存中占一个字节  
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());//一个字节一个字节的读
			System.out.println(bais.available());
			DataInputStream dis = new DataInputStream(bais);
			System.out.println(dis.readDouble());//先写进去的先读出来
			System.out.println(dis.readBoolean());
			dos.close();
			dis.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
