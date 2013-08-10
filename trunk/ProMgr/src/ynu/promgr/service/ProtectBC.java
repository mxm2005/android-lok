package ynu.promgr.service;

import ynu.promgr.timmer.Alarmreceiver;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

public class ProtectBC extends BroadcastReceiver {
	// 开机启动应用程序保护服务 让其在后台运行
	static final String ACTION = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION)) {
			SharedPreferences AppIni = context.getSharedPreferences(
					"configure", Context.MODE_WORLD_READABLE);
			boolean IsProtectLockOn = AppIni.getBoolean("IsProtectLockOn",
					false);
			if (IsProtectLockOn) {
				// Intent i = new Intent(context, Alarmreceiver.class);
				// i.setAction("arui.alarm.action");
				// PendingIntent sender = PendingIntent.getBroadcast(context, 0,
				// i, 0);
				// long firstime = SystemClock.elapsedRealtime();
				// AlarmManager am = (AlarmManager) context
				// .getSystemService(Context.ALARM_SERVICE);
				// // 10秒一个周期，不停的发送广播
				// am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				// firstime,
				// 10 * 1000, sender);
				context.startService(new Intent(context,
						AppProtectService.class));
			} else {
				context.stopService(new Intent(context, AppProtectService.class));
			}
		}
	}
}
