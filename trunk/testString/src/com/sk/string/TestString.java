package com.sk.string;

public class TestString {
	public static void main(String[] args) {
		String s = "aDdnfKFDKLjfFNL@!flFLnlfdl(8n@0@@#7@F84$6fdf86f^8DS5D4S4D";
		int lCount = 0, uCount = 0, oCount = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z') {
				lCount++;
			} else if (c >= 'A' && c <= 'Z') {
				uCount++;
			} else {
				oCount++;
			}
		}
//		Character.isUpperCase(c);
//		Character.isLowerCase(c);
		System.out.println(s.length());
		System.out.println(lCount);
		System.out.println(uCount);
		System.out.println(oCount);
	}
}
