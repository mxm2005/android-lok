package ynu.promgr.service;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

//监听保护的应用程序服务 一旦打开的程序是受到保护的对象 则弹出手势密码确认对话框
public class AppProtectService extends Service {

	static public ActivityManager mActivityManager;
	private int intGetTaskCounter = 15;
	private List<ActivityManager.RunningTaskInfo> mRunningTasks;

	private ArrayList<String> arylistProtectTaskPkg;
	private SharedPreferences AppIni;

	private boolean lockFlag;// true被锁
	private boolean IsProtectLockOn;
	private Intent Turn2ConfrimPassword;
	private Bundle band;
	private List<PackageInfo> mApps;
	private String tmpPkgName;// 用户解锁过重新打开的程序

	public void init() {
		loadApps();
		AppIni = getSharedPreferences("configure", Context.MODE_WORLD_READABLE
				| Context.MODE_WORLD_WRITEABLE);
		IsProtectLockOn = AppIni.getBoolean("IsProtectLockOn", false);
		if (IsProtectLockOn == false) {
			android.os.Process.killProcess(android.os.Process.myPid());
			stopSelf();
		}
		arylistProtectTaskPkg = new ArrayList<String>();

		for (int i = 0; i < mApps.size(); i++) {
			PackageInfo info = mApps.get(i);
			String packgName = info.packageName;
			if (AppIni.getBoolean(packgName + "CheckLockState", false)) {
				arylistProtectTaskPkg.add(packgName);
			}
		}

		mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		Turn2ConfrimPassword = new Intent();
		Turn2ConfrimPassword.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Turn2ConfrimPassword.setClassName("ynu.promgr",
				"ynu.promgr.ConfirmPasswordActivity");
		lockFlag = false;
		tmpPkgName = new String();
		band = new Bundle();
	}

	void loadApps() {
		PackageManager packageManager = this.getPackageManager();
		mApps = packageManager.getInstalledPackages(0);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.v("service", "create");
		init();
	}

	@Override
	public void onDestroy() {
		Log.v("service", "destroy");
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startid) {
		Log.v("service", "start");
		// 主要的监听保护应用程序的任务还是交给主线程里面的子线程处理
		new Thread(new Runnable() {
			public void run()// 实现监听被保护的应用程序
			{
				while (true) {
					// 获取当前正在运行的任务
					mRunningTasks = mActivityManager
							.getRunningTasks(intGetTaskCounter);

					// 关闭程序后，flag置false
					for (ActivityManager.RunningTaskInfo amTask : mRunningTasks) {
						String pkgName = amTask.baseActivity.getPackageName();
						if (tmpPkgName.length() != 0) {
							if (arylistProtectTaskPkg.contains(tmpPkgName)) {
								if (tmpPkgName.equals(pkgName)
										&& amTask.numRunning == 0)
									lockFlag = false;
							} else
								lockFlag = false;
						}
					}

					for (ActivityManager.RunningTaskInfo amTask : mRunningTasks) {
						String packageName = amTask.baseActivity
								.getPackageName();
						if (arylistProtectTaskPkg.contains(packageName)
								&& amTask.numRunning != 0// 应用程序是在前台执行而不是在后台运行
								&& lockFlag == false) {
							tmpPkgName = packageName;
							band.putString("PackageName", packageName);
							Turn2ConfrimPassword.putExtras(band);
							startActivity(Turn2ConfrimPassword);
							lockFlag = true;
							break;
						}
					}
					try {
						Thread.sleep(80);// 防止无限循环导致的应用程序占用CPU过高 所以加上sleep释放cpu
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
			}
		}).start();
	}
}