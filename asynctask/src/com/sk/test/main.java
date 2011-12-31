package com.sk.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class main extends Activity {
	private TextView tv;
	private MyTask task;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// TextView:显示进度
		tv = (TextView) findViewById(R.id.tv_progress);
		// 实例化AsyncTask线程
		task = new MyTask();
		// 启动AsyncTask线程
		task.execute(tv);
	}
}