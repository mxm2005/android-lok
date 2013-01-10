package com.wz.doctor.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.wz.doctor.R;
import com.wz.doctor.util.FileUtil;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateService extends Service {
	private static final int TIMEOUT = 10 * 1000;
	private static final String down_url = "http://mk.cdn.jccjd.com/cms/prod/upload/apks/48d/smart_52_3e55d32c5d211b70.apk";//你的apk下载地址
	private static final int DOWN_OK = 1;
	private static final int DOWN_ERROR = 0;
	private String app_name;
	private NotificationManager notificationManager;
	private Notification notification;
	private Intent updateIntent;
	private PendingIntent pendingIntent;
	private int notification_id = R.drawable.ic_launcher;
	private RemoteViews contentView;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		app_name = intent.getStringExtra("app_name");
		Log.i("", "file name: " + app_name);
		FileUtil.createFile(app_name);
		createNotification();
		createThread();
		return super.onStartCommand(intent, flags, startId);
	}

	/***
	 * 开线程下载
	 */
	public void createThread() {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case DOWN_OK:
					Log.i("", "handler down_ok");
					Log.i("", FileUtil.updateFile.getAbsolutePath());
					// 下载完成，点击安装
					Uri uri = Uri.fromFile(FileUtil.updateFile);
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setDataAndType(uri, "application/vnd.android.package-archive");
					pendingIntent = PendingIntent.getActivity(UpdateService.this, 0, intent, 0);
					notification.setLatestEventInfo(UpdateService.this, app_name, "下载成功，点击安装", pendingIntent);
					notificationManager.notify(notification_id, notification);
					startActivity(intent);
					stopSelf();
					break;
				case DOWN_ERROR:
					notification.setLatestEventInfo(UpdateService.this, app_name, "下载失败", pendingIntent);
					break;
				}
			}

		};

		final Message message = new Message();

		new Thread()
		{
			public void run()
			{
				try
				{
					long downloadSize = downloadUpdateFile(down_url, FileUtil.updateFile.toString());
					if(downloadSize > 0)
					{
						Log.i("", "download success..");
						message.what = DOWN_OK;
						handler.sendMessage(message);
					}

				}
				catch (Exception e)
				{
					Log.i("", "download fail");
					e.printStackTrace();
					message.what = DOWN_ERROR;
					handler.sendMessage(message);
				}
			};
		}.start();
	}


	public void createNotification() {
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
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

	public long downloadUpdateFile(String down_url, String file) throws Exception {
		int down_step = 5;
		int totalSize;
		int downloadCount = 0;
		int updateCount = 0;
		InputStream inputStream;
		OutputStream outputStream;

		URL url = new URL(down_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setConnectTimeout(TIMEOUT);
		httpURLConnection.setReadTimeout(TIMEOUT);

		totalSize = httpURLConnection.getContentLength();
		if (httpURLConnection.getResponseCode() == 404) {
			throw new Exception("fail!");
		}
		inputStream = httpURLConnection.getInputStream();
		outputStream = new FileOutputStream(file, false);
		byte buffer[] = new byte[1024];
		int readsize = 0;
		while ((readsize = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, readsize);
			downloadCount += readsize;
			if(updateCount == 0	|| ((int) (downloadCount / (float) totalSize * 100) - down_step) >= updateCount) {
				updateCount += down_step;
				contentView.setTextViewText(R.id.notificationPercent, updateCount + "%");
				contentView.setProgressBar(R.id.notificationProgress, 100, updateCount, false);
				notificationManager.notify(notification_id, notification);
			}

		}
		if (httpURLConnection != null) {
			httpURLConnection.disconnect();
		}
		inputStream.close();
		outputStream.close();
		return downloadCount;
	}

}
