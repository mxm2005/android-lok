package cn.m9.mobile;

import cn.m9.mobile.aidl.IMyService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestAidlClient extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private IMyService myService = null;
	private Button btnInvokeAIDLService;
	private Button btnBindAIDLService;
	private TextView textView;
	private static final String TAG = "TestAIDLClient";

	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub

//			myService = IMyService.Stub.asInterface(service);
//			btnInvokeAIDLService.setEnabled(true);
			myService = IMyService.Stub.asInterface(service);
			btnInvokeAIDLService.setEnabled(true);
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnInvokeAIDLService = (Button) findViewById(R.id.btnInvokeAIDLService);
		btnBindAIDLService = (Button) findViewById(R.id.btnBindAIDLService);
		btnInvokeAIDLService.setEnabled(false);
		textView = (TextView) findViewById(R.id.textview);
		btnInvokeAIDLService.setOnClickListener(this);
		btnBindAIDLService.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btnBindAIDLService:
			// °ó¶¨AIDL·þÎñ
			bindService(new Intent("cn.m9.mobile.aidl.IMyService"), serviceConnection, Context.BIND_AUTO_CREATE);
			break;
		case R.id.btnInvokeAIDLService:
			try {
				textView.setText(myService.getValue());
			} catch (Exception e) {
			    e.printStackTrace();
			}
			break;
		}
	}
}