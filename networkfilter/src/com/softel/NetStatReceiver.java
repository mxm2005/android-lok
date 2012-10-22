package com.softel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.SystemClock;

public class NetStatReceiver extends BroadcastReceiver {
	// 网络变化时所发的Intent的名字
	public static final String netACTION = "android.net.conn.CONNECTIVITY_CHANGE";
	public static final String NETSTAT_MONITOR = "com.softel.safebox.netstat";
	private int netstat = 2;
	public static boolean flag = false;
	
	Handler handler = new Handler();
	private int n = 0;

	@Override
	public void onReceive(final Context context, final Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(netACTION)) {
			// Intent中ConnectivityManager.EXTRA_NO_CONNECTIVITY这个关键字表示着当前是否连接上了网络
			boolean isBreak = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
			if (!isBreak) {//有网络
				NetworkInfo netInfo = getActiveNetwork(context);
				if (netInfo != null) {
					System.out.println(netInfo.getTypeName() + "网络变化" + netInfo.getType() + "--" + netInfo.getState());
					netstat = netInfo.getType();
					
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							// 要做的事情
							if (intent.getAction().equals(netACTION)) {
								n++;
//								Intent intent = new Intent(NETSTAT_MONITOR);
//								intent.putExtra("netstat", netstat);
//								context.sendBroadcast(intent);
								System.out.println("第" + n + "次^^^^^^^^^^^^^^^^ " );
							}
							handler.postDelayed(this, 2000);
							handler.removeCallbacks(this);
						}
					};
					
					handler.postDelayed(runnable, 2000);//每两秒执行一次runnable.
				}
//				System.out.println("flag : " + flag);
//				if (!flag) {
//					SystemClock.sleep(5000);
//					intent = new Intent(NETSTAT_MONITOR);
//					intent.putExtra("netstat", netstat);
//					context.sendBroadcast(intent);
//					flag = true;
//				}
			} else {//没网络
				netstat = 2;
			}
		}
	}
	
	
	/**
	 * 获取网络连接信息
	 * @param context
	 * @return
	 */
	private NetworkInfo getActiveNetwork(Context context) {
		if (context == null) return null;
		ConnectivityManager mConnMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (mConnMgr == null) return null;
		NetworkInfo aActiveInfo = mConnMgr.getActiveNetworkInfo(); // 获取活动网络连接信息
		return aActiveInfo;
	}

}
