package com.dialog.activity;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class TestActivity extends Activity implements OnClickListener,
		android.content.DialogInterface.OnClickListener {

	public static final String TAG = "TestActivity";

	private final int PROGRESS_DIALOG = 1;

	private ProgressDialog progressDialog = null;

	int i = 1;
	int fileCount = 0;
	
	int downSize = 0;
	
	private static final int TIMEOUT = 10 * 1000;
	private static final String down_url = "http://mk.cdn.jccjd.com/cms/prod/upload/apks/48d/smart_52_3e55d32c5d211b70.apk";//你的apk下载地址
	private static final int DOWN_OK = 1;
	private static final int DOWN_ERROR = 0;
	private String app_name;
	private NotificationManager notificationManager;
	private Notification notification;
	private Intent updateIntent;
	private PendingIntent pendingIntent;
	private int notification_id = R.drawable.icon;
	private RemoteViews contentView;

	Button button;
	// Button button2;
	FTPDataTransferListener listener = new FTPDataTransferListener() {
		long mTotalSize = 100;

		@Override
		public void transferred(long length) {   //显示已经传输的字节数
			Log.d(TAG, "transferred.length:" + length);
			int progress = (int) (length * 100.0 / mTotalSize);
			Message msg;
			msg = Message.obtain();
			msg.what = 1;
			msg.obj = progress;
			TestActivity.this.mHandler.sendMessage(msg);

		}

		@Override
		public void transferred(int length) {
			// TODO Auto-generated method stub
			downSize += length;
			int progress = (int) (downSize * 100.0 / fileSize);
			Message msg = Message.obtain();
			msg.what = 2;
			msg.obj = progress;
			mHandler.sendMessage(msg);
		}

		@Override
		public void started(long totalSize) {    //文件开始上传或下载时触发
			Log.d(TAG, "transferred.mTotalSize:" + totalSize);
			this.mTotalSize = totalSize;
			Message msg;

			msg = Message.obtain();
			msg.what = 2;
			msg.obj = (int) totalSize;
			TestActivity.this.mHandler.sendMessage(msg);

		}

		@Override
		public void started() {
			System.out.println("started");
			mHandler.sendEmptyMessage(1);
		}

		@Override
		public void failed() {  //传输失败时触发
			Log.d(TAG, "failed");
			Message msg;
			msg = Message.obtain();
			msg.what = 5;
			mHandler.sendMessage(msg);

		}

		@Override
		public void completed() {   //文件传输完成时，触发
//			Log.d(TAG, "completed");
//			Message msg;
//			msg = Message.obtain();
//			msg.what = 3;
//			mHandler.sendMessage(msg);
		}

		@Override
		public void aborted() {     //传输放弃时触发
			Log.d(TAG, "aborted");
			Message msg;
			msg = Message.obtain();
			msg.what = 4;
			mHandler.sendMessage(msg);
		}
	};

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int what = msg.what;
			Integer progress = (Integer) msg.obj;
			switch (what) {
			case 1:
//				createNotification();
				break;

			case 2:
				progressDialog.setProgress(progress);
//				contentView.setTextViewText(R.id.notificationPercent, progress + "%");
//				contentView.setProgressBar(R.id.notificationProgress, 100, progress, false);
//				notificationManager.notify(notification_id, notification);
				break;

			case 3:
				Uri uri = Uri.fromFile(new File("/mnt/sdcard/softel/safebox/weixin36android.apk"));
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setDataAndType(uri, "application/vnd.android.package-archive");
				startActivity(intent);
				break;

			case 4: // aborted
				// pb.setVisibility(View.GONE);
				System.out.println("aborted.....................");
				break;

			case 5: // failed
				// pb.setVisibility(View.GONE);
				break;
			}
		}
	};

	protected long fileSize;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		button = (Button) findViewById(R.id.button1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(PROGRESS_DIALOG);
				new Thread(downloadRun).start();
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESS_DIALOG:
			// this表示该对话框是针对当前Activity的
			progressDialog = new ProgressDialog(this);
			// 设置最大值为100
			progressDialog.setMax(100);
			// 设置进度条风格STYLE_HORIZONTAL
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setTitle("正在下载...1/"+fileSize);
			progressDialog.setCancelable(false);
			progressDialog.setButton("取消", this);
			break;
		}
		return progressDialog;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case PROGRESS_DIALOG:
			// 将进度条清0
			progressDialog.incrementProgressBy(-progressDialog.getProgress());
			break;
		}
	}
	FTPClient myFtp = null;
	Runnable downloadRun = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			myFtp = new FTPClient();
			try {
				myFtp.connect("192.168.1.100", 21); // 连接
				myFtp.login("cy", "cy"); // 登录
				myFtp.changeDirectory("/"); // 改变当前文件夹
//				FTPFile [] list = myFtp.list("*.*");  //使用通配浏览文件
				fileSize = myFtp.fileSize("/weixin36android.apk");
				myFtp.download("weixin36android.apk", new File("/mnt/sdcard/softel/safebox/weixin36android.apk"), listener);  //断点续传
//				String path="mnt/sdcard/sdcard/test/";   //客户端文件路径
//				File file = new File(path);
//				  String test[];
//				  test=file.list();
//				  fileCount=test.length;
//				  for(i=1;i<=test.length;i++)
//				  {
//					  myFtp.upload(new File(path + test[i-1]), listener);  //上传
//					  //myFtp.download(test[i-1], new File(path+i+".jpg"), listener);   //下载
//				  }
				  
				progressDialog.dismiss();
				myFtp.disconnect(true);
			} catch (Exception e) {
				e.printStackTrace();
			} 

		}
	};

	@Override
	public void onClick(View v) {
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (which == dialog.BUTTON1) {
			try {
				myFtp.abortCurrentDataTransfer(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Toast.makeText(TestActivity.this, "取消操作", Toast.LENGTH_SHORT)
					.show();
		}

	}
	
	public void createNotification() {
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notification = new Notification();
		notification.icon = R.drawable.icon;
		notification.tickerText = "开始下载";
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		
		contentView = new RemoteViews(getPackageName(), R.layout.notification_item);
		contentView.setTextViewText(R.id.notificationTitle, "正在下载");
		contentView.setTextViewText(R.id.notificationPercent, "0%");
		contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);

		notification.contentView = contentView;
		updateIntent = new Intent();
		updateIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);
		notification.contentIntent = pendingIntent;
		notificationManager.notify(notification_id, notification);
	}
}
