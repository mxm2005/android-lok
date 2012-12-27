package cn.hnu.main.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
	final String shared_pre_data = "shared_pre_data";
	static SharedPreferencesUtil spUtil = null;
	private SharedPreferences sp = null;

	public SharedPreferencesUtil(Context context) {
		sp = context.getSharedPreferences(shared_pre_data, 0);
	}

	public static SharedPreferencesUtil getInstance(Context context) {
		if (spUtil == null) {
			spUtil = new SharedPreferencesUtil(context);
		}
		return spUtil;
	}

	public void setSharedString(String key, String value) {
		sp.edit().putString(key, value).commit();
	}

	public String getSharedString(String key) {
		return sp.getString(key, "");
	}

	public void setSharedInt(String key, int value) {
		sp.edit().putInt(key, value).commit();
	}

	public int getSharedInt(String key) {
		return sp.getInt(key, 0);
	}
	public void setSharedBoolean(String key, boolean value) {
		sp.edit().putBoolean(key, value).commit();
	}

	public boolean getSharedBoolean(String key) {
		return sp.getBoolean(key, false);
	}
}
