package cn.hnu.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBrocast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent service = new Intent(context, MyService.class);
			context.startService(service);
		}
	}
}
