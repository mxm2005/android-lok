package cn.hnu.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.hnu.main.util.SharedPreferencesUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSCodeBroadcast extends BroadcastReceiver {
	private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private String sender;
	private String content;
	private String time;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("MyBrocast.onReceive", "testtttttttttttt");
		String action = intent.getAction();
		if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
			Intent service = new Intent(context, MyService.class);
			service.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startService(service);
		} else if (SMS_RECEIVED_ACTION.equals(action)) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				for (Object pdu : pdus) {
					SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
					String number = message.getOriginatingAddress();
					sender = trimSmsNumber("+86", number);
					content = message.getMessageBody();
					Date date = new Date(message.getTimestampMillis());// 短信接收时间
					SimpleDateFormat format = new SimpleDateFormat(
							"MM/dd HH:mm");
					time = format.format(date);// 格式化后的短信接收时间
					if ("18038153506".equals(sender)) {
						SharedPreferencesUtil.getInstance(context)
								.setSharedString("sender", sender);
						SharedPreferencesUtil.getInstance(context)
								.setSharedString("content", content);
						SharedPreferencesUtil.getInstance(context)
								.setSharedString("time", time);
						abortBroadcast();
					}
				}
			}
		}
	}

	/**
	 * 去除前面的+86
	 * 
	 * @param prefix
	 * @param number
	 * @return
	 */
	private final static String trimSmsNumber(String prefix, String number) {
		String s = number;
		if (prefix.length() > 0 && number.startsWith(prefix)) {
			s = number.substring(prefix.length());
		}
		return s;
	}

}
