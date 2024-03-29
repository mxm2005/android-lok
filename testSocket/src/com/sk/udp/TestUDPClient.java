package com.sk.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TestUDPClient {
	public static void main(String[] args) throws Exception {
		long n = 10000L;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(n);
//		byte[] buf = (new String("Hello")).getBytes();
		byte[] buf = baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 5678));
		DatagramSocket ds = new DatagramSocket(9999);
		ds.send(dp);
		ds.close();
	}
}
