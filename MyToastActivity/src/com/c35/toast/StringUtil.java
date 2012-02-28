package com.c35.toast;

import android.util.Log;

public class StringUtil {

	public static String parseString(String s,int lengh){
		String s1="";
		int length=0;
		if(s.getBytes().length>=lengh){
			while( s1.getBytes().length<lengh){
				s1 += s.toCharArray()[length++];
			}
			s1+="...";
		}else{
			s1=s;
		}
		Log.i("StringUtil", s1);
		return s1;
	}
}
