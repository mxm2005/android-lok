package com.learn;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridView;

public class ListGestures extends Activity {
	private GestureLibrary library;
	public static String[] gesName;

	public static Bitmap[] pics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allgesture);
		load();
		GridAdapter adapter = new GridAdapter(this);
		GridView grv = (GridView) findViewById(R.id.gvTop);
		grv.setAdapter(adapter);

	}

	public void load() {
		String path = "/sdcard/gestures";
		// 加载手势文件
		library = GestureLibraries.fromFile(path);

		if (library.load()) {
			int index = library.getGestureEntries().size();
			pics = new Bitmap[index];
			gesName=new String[index];
			int i = 0;
			//获得所有手势文件，返回的是存储key的set集合
			for (String name : library.getGestureEntries()) {
				// 因为在手势仓库中，支持一个name对应多个手势文件，
				// 所以会返回一个list，在这里我们取list里的第一个
				ArrayList<Gesture> geess = library.getGestures(name);
				Gesture gg = geess.get(0);
				//将手势文件转成位图
				Bitmap bmp = gg.toBitmap(100, 100, 12, Color.BLUE);
				pics[i] = bmp;//保存位图
				gesName[i]=name;//保存当前的手势名称。
				i++;
			}
		}
	}
}
