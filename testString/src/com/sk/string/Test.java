package com.sk.string;

public class Test {
	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hello";
		String s3 = "hello";
		System.out.println(s1 == s2);
		
		s1 = new String("hello");
		s2 = new String("hello");
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		
		char[] c = {'s', 'u', 'n', ' ', 'j', 'a', 'v', 'a'};
		String s4 = new String(c);
		String s5 = new String(c, 4, 4);
		System.out.println(s4);
		System.out.println(s5);
	}
}
