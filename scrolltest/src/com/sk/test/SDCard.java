package com.sk.test;

import java.io.File;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class SDCard extends Activity {
	File imagepath;
	
	private final BroadcastReceiver broadcastRec = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals("android.intent.action.MEDIA_MOUNTED"))//SD 
				// 卡已经成功挂载
			{
				imagepath = android.os.Environment.getExternalStorageDirectory();//你的SD卡路径
			} else 
				if(intent.getAction().equals("android.intent.action.MEDIA_REMOVED"))//各种未挂载状态
//						||intent.getAction().equals("android.intent.action.ACTION_MEDIA_UNMOUNTED")
//						||intent.getAction().equals("android.intent.action.ACTION_MEDIA_BAD_REMOVAL"))
				{
					imagepath = android.os.Environment.getDataDirectory();//你的本地路径
				}
		}
	};
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//在IntentFilter中选择你要监听的行为
		IntentFilter intentFiltera = new IntentFilter(Intent.ACTION_MEDIA_MOUNTED);
		
		intentFiltera.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		intentFiltera.addAction(Intent.ACTION_MEDIA_REMOVED);
			//intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);
		intentFiltera.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
			//intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
			//intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		intentFiltera.addDataScheme("file");
			registerReceiver(broadcastRec, intentFiltera);//注册监听函数
			unregisterReceiver(broadcastRec);//使用完注销广播监听函数
		
	}

}
