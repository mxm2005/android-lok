package com.demo.progress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AndProgressActivity extends Activity implements DialogInterface.OnClickListener {
	private static ProgressDialog pd;
	private MyHandler myHandler = null;
	private MyRun myRun = null;
	private Message msg = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pd = ProgressDialog.show(AndProgressActivity.this, "等待..", "连接中,请稍后..",
				true, true);
		myHandler = new MyHandler();
		myRun = new MyRun();
		myRun.start();

		// AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setMessage("Are you sure you want to exit?")
		// .setCancelable(false)
		// .setPositiveButton("Yes",this)
		// .setNegativeButton("No", this);
		// AlertDialog alert = builder.create();
		// alert.show();

	}

	/**
	 * 关闭Dialog
	 */
	public static void closeProgressDialog() {
		if (null != pd) {
			pd.dismiss();
		}
	}

	/***
	 * 
	 * 
	 * @author zhou_zheng
	 * 
	 */
	class MyRun extends Thread {
		int sum = 0;

		public void run() {
			for (int i = 0; i < 1000; i++) {
				sum += i;
				System.out.println("==============" + i
						+ "==================================");
				System.out
						.println("================================================");
			}

			// Process pro;
			// InputStream is ;
			// int t = 0 ;
			// byte[] retStr = null ;
			// try {
			// pro = Runtime.getRuntime().exec("ping 10.30.0.32");
			// is = pro.getInputStream();
			// retStr = new byte[300];
			// t = is.read(retStr);
			// pro.destroy();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// String str = new String(retStr,0,t);
			// Log.e(" ip info ", str);
			// try {
			// Thread.sleep(10000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			msg = myHandler.obtainMessage();
			msg.arg1 = 0x1;
			// 发送消息
			msg.sendToTarget();
		}
	};

	/**
	 * Handler 关闭进度条
	 * 
	 * @author zhou_zheng
	 * 
	 */
	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.arg1) {
			case 0x1:
				closeProgressDialog();
				Intent intent = new Intent();
				intent.putExtra("result", "数据已经加载完成~");

				setResult(TestAct.RESULT_CODE, intent);
				AndProgressActivity.this.finish();
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		Log.e("---------------", which + "");
		switch (which) {
		case -1:
			Log.i("info", "onClick YES~");
			AndProgressActivity.this.finish();
			break;
		case -2:
			Log.i("info", "onClick NO~");
			dialog.cancel();
			break;
		default:
			break;
		}
	}
}