package cn.etzmico.dmp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class deviceActivity extends Activity {
	static final int RESULT_ENABLE = 1;
	DevicePolicyManager mDPM;
	ActivityManager mAM;
	ComponentName mDeviceComponentName;
	Button enableAdmin, disableAdmin, force_lock, btn_time_out, reset;
	EditText et;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		mAM = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		mDeviceComponentName = new ComponentName(deviceActivity.this,
				deviceAdminReceiver.class);
		setContentView(R.layout.main);

		findView();

		init();

	}

	void findView() {
		enableAdmin = (Button) findViewById(R.id.enable_admin);
		disableAdmin = (Button) findViewById(R.id.disable_admin);
		force_lock = (Button) findViewById(R.id.force_lock);
		btn_time_out = (Button) findViewById(R.id.time_out);
		et = (EditText) findViewById(R.id.et_time_out);
		reset = (Button) findViewById(R.id.reset);
	}

	void init() {
		enableAdmin.setOnClickListener(new enableAdminClickEvent());
		disableAdmin.setOnClickListener(new disableAdminClickEvent());
		force_lock.setOnClickListener(new force_lock());
		btn_time_out.setOnClickListener(new timeoutClickEvent());
		reset.setOnClickListener(new resetClickEvent());
	}

	void updateButtonState() {
		boolean active = mDPM.isAdminActive(mDeviceComponentName);
		if (active) {
			enableAdmin.setEnabled(false);
			disableAdmin.setEnabled(true);
			force_lock.setEnabled(true);
			btn_time_out.setEnabled(true);
			reset.setEnabled(true);
		} else {
			enableAdmin.setEnabled(true);
			disableAdmin.setEnabled(false);
			force_lock.setEnabled(false);
			btn_time_out.setEnabled(false);
			reset.setEnabled(false);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateButtonState();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case RESULT_ENABLE:
			if (resultCode == Activity.RESULT_OK) {
				Log.v("DeviceEnable", "deviceAdmin:enable");
			} else {
				Log.v("DeviceEnable", "deviceAdmin:disable");
			}
			break;

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 设备管理可用的点击事件
	 * 
	 * @author terry
	 * 
	 */
	class enableAdminClickEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceComponentName);
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "(可自定义区域2)");
			startActivityForResult(intent, RESULT_ENABLE);
		}

	}

	/**
	 * 设备管理不可用的点击事件
	 * 
	 * @author terry
	 * 
	 */
	class disableAdminClickEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mDPM.removeActiveAdmin(mDeviceComponentName);
			updateButtonState();
		}

	}

	/**
	 * 锁屏
	 * 
	 * @author terry
	 * 
	 */
	class force_lock implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (ActivityManager.isUserAMonkey()) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						deviceActivity.this);
				builder.setMessage("你不能对此屏幕进行操作，因为你不是管理员");
				builder.setPositiveButton("I admit defeat", null);
				builder.show();
				return;
			}
			boolean active = mDPM.isAdminActive(mDeviceComponentName);
			if (active) {
				// ""中为设置的PIN密码，默认为123456
				mDPM.resetPassword("123456",
						DevicePolicyManager.RESET_PASSWORD_REQUIRE_ENTRY);
				mDPM.lockNow();
			}
		}
	}

	/**
	 * 屏幕自动变暗
	 * 
	 * @author terry
	 * 
	 */
	class timeoutClickEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (ActivityManager.isUserAMonkey()) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						deviceActivity.this);
				builder.setMessage("你不能对我的屏幕进行操作，因为你不是管理员");
				builder.setPositiveButton("I admit defeat", null);
				builder.show();
				return;
			}
			boolean active = mDPM.isAdminActive(mDeviceComponentName);
			if (active) {
				long timeout = 1000L * Long.parseLong(et.getText().toString());
				mDPM.setMaximumTimeToLock(mDeviceComponentName, timeout);
			}
		}
	}

	/**
	 * 恢复出厂设置
	 * 
	 * @author terry
	 * 
	 */
	class resetClickEvent implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (ActivityManager.isUserAMonkey()) {
				// Don't trust monkeys to do the right thing!
				AlertDialog.Builder builder = new AlertDialog.Builder(
						deviceActivity.this);
				builder
						.setMessage("You can't wipe my data because you are a monkey!");
				builder.setPositiveButton("I admit defeat", null);
				builder.show();
				return;
			}

			AlertDialog.Builder builder = new Builder(deviceActivity.this);
			builder.setMessage("将重置数据，你确定此操作吗？");
			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							AlertDialog.Builder aler = new AlertDialog.Builder(
									deviceActivity.this);
							aler.setMessage("删除数据后，系统将会重新启动.确定吗？");
							aler.setPositiveButton(android.R.string.ok,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											boolean active = mDPM
													.isAdminActive(mDeviceComponentName);
											if (active) {
												mDPM.wipeData(0);
											}
										}
									});
							aler
									.setNeutralButton(android.R.string.cancel,
											null);
							aler.show();
						}
					});
			builder.setNeutralButton(android.R.string.cancel, null);
			builder.show();
		}
	}

}