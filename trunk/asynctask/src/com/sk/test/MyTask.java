package com.sk.test;

import android.os.AsyncTask;
import android.widget.TextView;

public class MyTask extends AsyncTask<Object, Integer, Integer> {

	private TextView tv;

	// 继承AsyncTask类，覆盖以下5个方法
	/**
	 * Runs on the UI thread before doInBackground(Params...).
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Integer doInBackground(Object... params) {
		tv = (TextView) params[0];
		int i;
		// 循环100次，每次休眠0.5秒
		for (i = 1; i <= 20; i++) {
			publishProgress(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	/**
	 * Runs on the UI thread after publishProgress(Progress...) is invoked. 
	 * The specified values are the values passed to publishProgress(Progress...).
	 */
	@Override
	protected void onProgressUpdate(Integer... progress) {
		int i = progress[0];
		switch (i % 6) {
		case 1:
			tv.setText("等待服务器响应: .");
			break;
		case 2:
			tv.setText("等待服务器响应: . .");
			break;
		case 3:
			tv.setText("等待服务器响应: . . .");
			break;
		case 4:
			tv.setText("等待服务器响应: . . . .");
			break;
		case 5:
			tv.setText("等待服务器响应: . . . . .");
			break;
		case 0:
			tv.setText("等待服务器响应: . . . . . .");
			break;
		}
	}

	/**
	 * Runs on the UI thread after doInBackground(Params...). The specified result is the value returned by doInBackground(Params...).
	 */
	@Override
	protected void onPostExecute(Integer result) {
		tv.setText("完成。");
	}

	/**
	 * Runs on the UI thread after cancel(boolean) is invoked and doInBackground(Object[]) has finished.
	 */
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

}
