package com.sk;

public class Test {
	public static void main(String[] args) {
//		String path = Test.class.getResource("/").getPath() + "key.xml" ;
//		System.out.println(path);
//		String key = ReadXMLUtil.getKey(path);
//		DES des = new DES(key);
//		String encryptResult = des.encrypt("adb");
//		System.out.println(encryptResult);
//		String reDate = des.decrypt(encryptResult);
//		System.out.println(reDate);
		
		DES des = new DES("adadadadad");
		String encryptResult = des.encrypt("7223279");
		System.out.println(encryptResult);
		String decryptResult = des.decrypt(encryptResult);
		System.out.println(decryptResult);
	}
}
