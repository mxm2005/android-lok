package cn.hnu.main;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		IntentFilter localIntentFilter = new IntentFilter(
				"android.provider.Telephony.SMS_RECEIVED");
		localIntentFilter.setPriority(2147483647);
		MyBrocast localMessageReceiver = new MyBrocast();
		Log.v("MyBrocast.onReceive", "onCreate");
		Intent localIntent = registerReceiver(localMessageReceiver,
				localIntentFilter);
		super.onCreate();
	}
}
