package z.s.test.service;

import java.util.ArrayList;

import z.s.test.MyApplication;
import z.s.test.bean.ContactInfo;
import z.s.test.util.MAsyncTask;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;

public class T9Service extends Service
{

	private AsyncQueryHandler asyncQuery;

	@Override
	public void onCreate()
	{
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
	}

	@Override
	public void onDestroy()
	{
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{

		System.out.println("T9Service-begin");
		asyncQuery = new MyAsyncQueryHandler(getContentResolver());

		// DownLoadBean dlb = gson.fromJson(url, DownLoadBean.class);

		// downLoading(dlb);

		initSQL();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onRebind(Intent intent)
	{
	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		return true;
	}

	protected void initSQL()
	{
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = { ContactsContract.CommonDataKinds.Phone._ID,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.DATA1, "sort_key",
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID, };
		asyncQuery.startQuery(0, null, uri, projection, null, null, "sort_key COLLATE LOCALIZED asc");
	}

	@SuppressLint("HandlerLeak")
	private class MyAsyncQueryHandler extends AsyncQueryHandler
	{
		public MyAsyncQueryHandler(ContentResolver cr)
		{
			super(cr);
		}

		protected void onQueryComplete(int token, Object cookie, Cursor cursor)
		{
			querying(cursor);
		}
	}

	@SuppressLint("HandlerLeak")
	private void querying(final Cursor cursor)
	{

		Handler handlerInsertOrder = new Handler()
		{
			public void handleMessage(Message msg)
			{
				switch (msg.what)
				{
				case MAsyncTask.DOWNLOADING_START_MESSAGE:
					System.out.println("开始整理联系人");
					break;
				case MAsyncTask.DOWNLOAD_END_MESSAGE:
					Bundle bundle1 = msg.getData();
					@SuppressWarnings("unchecked") 
					ArrayList<ContactInfo> list = (ArrayList<ContactInfo>) bundle1.get("完成");
					MyApplication ma = (MyApplication) getApplication();
					System.out.println(list.size());
					ma.setContactInfo(list);
					for(ContactInfo ci : list)
					{
						Log.i("", ci.getName());
						Log.i("", ci.getPhoneNum());
						Log.i("", ci.getFormattedNumber());
						Log.i("", ci.getPinyin());
						Log.i("", "--------------------------------");
					}
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};

		MAsyncTask.startRequestServerData(T9Service.this, handlerInsertOrder, cursor);
	}
}
