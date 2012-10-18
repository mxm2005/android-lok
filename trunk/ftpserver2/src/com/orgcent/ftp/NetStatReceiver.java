package com.orgcent.ftp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetStatReceiver extends BroadcastReceiver {
	// android 中网络变化时所发的Intent的名字
	public static final String netACTION = "android.net.conn.CONNECTIVITY_CHANGE";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(netACTION)) {
			System.out.println("oooooooooooooooooooooooooooooooooooooooooo");
			// Intent中ConnectivityManager.EXTRA_NO_CONNECTIVITY这个关键字表示着当前是否连接上了网络
			// true 代表网络断开 false 代表网络没有断开
			boolean isBreak = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
			if (!isBreak) {
				NetworkInfo netInfo = getActiveNetwork(context);
				if (netInfo != null) {
					System.out.println(netInfo.getTypeName() + "网络变化" + netInfo.getType() + "--" + netInfo.getState());
				}
			}
		}
	}
	
//	private void registerDateTransReceiver() {
//		IntentFilter filter = new IntentFilter();
//		filter.addAction(netACTION);
//		filter.setPriority(1000);
//		registerReceiver(new NetCheckReceiver(), filter);
//	}
	
	public static NetworkInfo getActiveNetwork(Context context) {
		if (context == null)
			return null;
		ConnectivityManager mConnMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (mConnMgr == null)
			return null;
		NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo(); // 获取活动网络连接信息
		return aActiveInfo;
	}

}
