package com.sk;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;  
import android.widget.Toast;
  
/* 
 * 接收静态注册广播的BroadcastReceiver， 
 * step1:要到AndroidManifest.xml这里注册消息 
 *      <receiver android:name="clsReceiver2"> 
            <intent-filter> 
                <action 
                    android:name="com.testBroadcastReceiver.Internal_2"/> 
            </intent-filter> 
        </receiver> 
    step2:定义消息的字符串 
    step3:通过Intent传递消息来驱使BroadcastReceiver触发 
 */  
public class clsReceiver2 extends BroadcastReceiver {
	
	NotificationManager mn = null;
	Notification notification = null;
	Context context = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Toast.makeText(context, "成功了？？？", 1000).show();
		System.out.println("静态receiver");
		
	}
}