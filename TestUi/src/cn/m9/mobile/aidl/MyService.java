package cn.m9.mobile.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
	public class MyServiceImpl extends IMyService.Stub {
		public String getValue() {
			return "Android is very powerful";
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new MyServiceImpl();
	}
}