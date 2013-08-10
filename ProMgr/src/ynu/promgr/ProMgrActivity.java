package ynu.promgr;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import ynu.model.ViewHolder;
import ynu.promgr.service.AppProtectService;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class ProMgrActivity extends ListActivity {

	private boolean IsAbnormalExit;// 是否非正常退出
	private int AppTotal;// app总数
	private int AppProtectNum;
	private boolean IsShowSys = false;
	private List<PackageInfo> mApps; // 程序list(包含系统app)
	private List<PackageInfo> userApps; // 用户程序list
	private ProgressDialog mProgressDialog = null; // 进度条对话框

	private ToggleButton OpenLockProtect;
	private boolean IsProtectLockOn;

	private ToggleButton OpenIptablesProtect;
	private boolean IsProtectIptablesOn;

	private SharedPreferences AppIni; // 存储类

	static final int RESULTFROMSETPASSWORD = 1000; // 设置密码requestcode
	static final int RESULTFROMCONFIRMPASSWORD = 1001; // 确认密码requestcode

	private MyAdapter adapter;
	private boolean[] CheckLockState;
	private boolean[] CheckNetState;
	private boolean[] CheckWifiState;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.promgr);

		IsAbnormalExit = false;
		// Lock开关
		OpenLockProtect = (ToggleButton) findViewById(R.id.OpenLockProtect);
		OpenLockProtect.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				IsProtectLockOn = OpenLockProtect.isChecked();
				if (IsShowSys)
					ChangeListView(mApps);
				else
					ChangeListView(userApps);
			}
		});

		// 防火墙开关
		OpenIptablesProtect = (ToggleButton) findViewById(R.id.OpenIptablesProtect);
		OpenIptablesProtect.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				IsProtectIptablesOn = OpenIptablesProtect.isChecked();
			}
		});

		loadApps();
		LoadIniData();
		adapter = new MyAdapter(this, userApps);
		setListAdapter(adapter);

		AppProtectNum = 0;

		boolean FirstTimeOpenApp = AppIni.getBoolean("FirstTimeOpenApp", true);
		if (FirstTimeOpenApp) {
			Intent i = new Intent();
			i.setClass(ProMgrActivity.this, SetPasswordActivity.class);
			startActivityForResult(i, RESULTFROMSETPASSWORD);
		} else {
			Intent i = new Intent();
			Bundle band = new Bundle();
			band.putString("PackageName", "");
			band.putString("ClassName", "");
			i.putExtras(band);
			i.setClass(ProMgrActivity.this, ConfirmPasswordActivity.class);
			startActivityForResult(i, RESULTFROMCONFIRMPASSWORD);
		}
	}

	protected void onActivityResult(int resquestcode, int resultcode,
			Intent data) {
		if (resultcode == RESULTFROMCONFIRMPASSWORD
				|| resultcode == RESULTFROMSETPASSWORD) {
			IsAbnormalExit = true;
			ProMgrActivity.this.finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 菜单按钮
		menu.add(0, 1, 1, R.string.pro_refresh)
				.setIcon(R.drawable.menu_refresh);
		menu.add(0, 2, 2, R.string.pro_show_sys).setIcon(
				R.drawable.menu_show_sys);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 菜单响应
		if (item.getItemId() == 1) {
			ChangeListView(userApps);// 刷新，重新提取apps
			IsShowSys = false; // 是否显示系统app改为否
		}
		if (item.getItemId() == 2) {
			if (IsShowSys == false) {
				ChangeListView(mApps);
				item.setTitle(R.string.pro_hide_sys);
				IsShowSys = true;
			} else {
				ChangeListView(userApps);
				item.setTitle(R.string.pro_show_sys);
				IsShowSys = false;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			new AlertDialog.Builder(this)
					.setMessage(R.string.exitinfo)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (IsAbnormalExit == false) {
										SaveIniData();
									}
									ProMgrActivity.this.finish();
								}
							})
					.setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		}
		return super.onKeyDown(keyCode, event);
	}

	// 读出信息
	public void LoadIniData() {
		AppIni = getSharedPreferences("configure", Context.MODE_WORLD_WRITEABLE
				| Context.MODE_WORLD_READABLE);
		IsProtectLockOn = AppIni.getBoolean("IsProtectLockOn", false);
		OpenLockProtect.setChecked(IsProtectLockOn);
		AppProtectNum = AppIni.getInt("AppProtectNum", 0);
	}

	// 存储信息
	public void SaveIniData() {
		SharedPreferences.Editor mEditor = AppIni.edit();
		mEditor.putBoolean("IsProtectLockOn", IsProtectLockOn);
		mEditor.commit();
		AppIni.edit().putInt("AppTotal", AppTotal).commit();
		AppIni.edit().putInt("AppProtectNum", AppProtectNum).commit();
		if (IsProtectLockOn) {
			stopService(new Intent(this, AppProtectService.class));
			startService(new Intent(this, AppProtectService.class));
		} else {
			stopService(new Intent(this, AppProtectService.class));
		}
	}

	// 读取程序
	void loadApps() {
		userApps = new ArrayList<PackageInfo>();
		PackageManager packageManager = this.getPackageManager();
		List<PackageInfo> paklist = packageManager.getInstalledPackages(0);
		mApps = packageManager.getInstalledPackages(0);
		for (int i = 0; i < paklist.size(); i++) {
			PackageInfo pak = (PackageInfo) paklist.get(i);
			// 判断是否为非系统预装的应用程序
			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
				userApps.add(pak);
			}
		}
	}

	// 更新listview的数据
	void ChangeListView(List<PackageInfo> apps) {
		adapter.setData(apps);
		adapter.notifyDataSetChanged();
	}

	// 显示进度条对话框
	void showProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage(getString(R.string.pro_watting));
		mProgressDialog.setCancelable(false);// 设置是否按back键取消
		mProgressDialog.show();
	}

	// 销毁进度条对话框
	void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	public class MyAdapter extends BaseAdapter {
		private ViewHolder holder;
		private LayoutInflater mInflater;
		private List<PackageInfo> apps;

		public MyAdapter(Context context, List<PackageInfo> apps) {
			this.mInflater = LayoutInflater.from(context);
			this.apps = apps;
			CheckLockState = new boolean[getCount()];
		}

		public void setData(List<PackageInfo> apps) {
			this.apps = apps;
			CheckLockState = new boolean[getCount()];
		}

		public int getCount() {
			AppTotal = apps.size();
			return AppTotal;
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return arg0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.prolist, null);
				holder.AppIcon = (ImageView) convertView
						.findViewById(R.id.AppIcon);
				holder.AppName = (TextView) convertView
						.findViewById(R.id.AppName);
				holder.AppLockState = (ImageButton) convertView
						.findViewById(R.id.AppLockState);
				// holder.AppNetState = (ImageView) convertView
				// .findViewById(R.id.AppNetState);
				// holder.AppWifiState = (ImageView) convertView
				// .findViewById(R.id.AppWifiState);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			PackageManager pm = getPackageManager(); // 得到pm对象
			PackageInfo Info = apps.get(position);
			ApplicationInfo applicationInfo = Info.applicationInfo;

			holder.AppIcon.setImageDrawable(pm
					.getApplicationIcon(applicationInfo));
			holder.AppName.setText(pm.getApplicationLabel(applicationInfo));
			holder.AppLockState.setOnClickListener(new ButtonListener(
					holder.AppLockState, position));

			CheckLockState[position] = AppIni.getBoolean(Info.packageName
					+ "CheckLockState", false);
			if (CheckLockState[position]) {
				if (IsProtectLockOn) {
					holder.AppLockState.setEnabled(true);
					holder.AppLockState
							.setImageResource(R.drawable.lock_enbled);
				} else {
					holder.AppLockState.setEnabled(false);
					holder.AppLockState
							.setImageResource(R.drawable.lock_disenbled);
				}
			} else {
				if (IsProtectLockOn) {
					holder.AppLockState.setEnabled(true);
					holder.AppLockState
							.setImageResource(R.drawable.unlock_enbled);
				} else {
					holder.AppLockState.setEnabled(false);
					holder.AppLockState
							.setImageResource(R.drawable.unlock_disenbled);
				}
			}
			return convertView;
		}

		class ButtonListener implements OnClickListener {
			private int position;
			private ImageButton tmp;

			ButtonListener(ImageButton tmp, int pos) {
				this.tmp = tmp;
				position = pos;
			}

			public void onClick(View v) {
				PackageInfo Info = apps.get(position);
				String packagename = Info.packageName;
				int vid = v.getId();
				if (vid == holder.AppLockState.getId()) {
					if (CheckLockState[position] == false) {
						AppProtectNum++;
						AppIni.edit().putInt("AppProtectNum", AppProtectNum)
								.commit();
						AppIni.edit()
								.putBoolean(packagename + "CheckLockState",
										true).commit();
						tmp.setImageResource(R.drawable.lock_enbled);
						CheckLockState[position] = true;
					} else if (CheckLockState[position] == true) {
						AppProtectNum--;
						AppIni.edit().remove(packagename + "CheckLockState")
								.commit();
						tmp.setImageResource(R.drawable.unlock_enbled);
						CheckLockState[position] = false;
					}
				}
			}

		}
	}
}
