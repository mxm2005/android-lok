package com.wz.doctor;

import java.io.File;

import com.wz.doctor.util.FileUtil;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;

public class MyApplication extends Application {

	public static int localVersion = 0;// 本地安装版本

	public static int serverVersion = 0;// 服务器版本，从服务器获取版本号

	public static String downloadDir = "doctor/";// 安装目录

	@Override
	public void onCreate() {
		super.onCreate();
		if(android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
				.getExternalStorageState()))
		{
			File cache = new File(Environment.getExternalStorageDirectory() + "/"
					+ MyApplication.downloadDir + "/memo");
			if (!cache.exists()) {
				cache.mkdir();
		     }
		}
		try {
			PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
			localVersion = packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

	}

}
