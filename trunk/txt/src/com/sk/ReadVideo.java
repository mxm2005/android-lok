package com.sk;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

public class ReadVideo {
	public static void main(String[] args) {
		File source = new File("G:\\飘花电影piaohua.com武侠DVD国语中字.rmvb");
		Encoder encoder = new Encoder();
		try {
			MultimediaInfo m = encoder.getInfo(source);
			long ls = m.getDuration();
			System.out.println("此视频时长为:" + ls / 60000 + "分" + (ls % 60000) / 1000 + "秒！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
