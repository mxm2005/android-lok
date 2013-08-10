package ynu.promgr;

import java.util.ArrayList;
import java.util.List;

import ynu.util.LockPatternUtils;
import ynu.widget.LockPatternView;
import ynu.widget.LockPatternView.Cell;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmPasswordActivity extends Activity {

	private SharedPreferences AppIni;

	private String packageName;

	private Intent i;
	private boolean isPasswordRight;
	private Button btnCpLeft, btnCpRight;
	private TextView TopText, BottomText;
	private LockPatternView mLockPatternView;

	private ActivityManager mActivityManager;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmpassword);
		
		Log.i("", "confirm password activity.");

		i = new Intent();
		i = ConfirmPasswordActivity.this.getIntent();
		isPasswordRight = false;
		Bundle dd = this.getIntent().getExtras();
		mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		packageName = dd.getString("PackageName");
		btnCpLeft = (Button) findViewById(R.id.btnCpLeft);
		btnCpRight = (Button) findViewById(R.id.btnCpRight);
		TopText = (TextView) findViewById(R.id.CpTopText);
		BottomText = (TextView) findViewById(R.id.CpBottomText);
		mLockPatternView = (LockPatternView) findViewById(R.id.CpLockPattern);

		TopText.setText(R.string.confirmpassword);
		BottomText.setText(R.string.need_to_unlock);
		btnCpLeft.setText(R.string.retry);
		btnCpRight.setText(R.string.forgetpassword);

		AppIni = getSharedPreferences("configure", Context.MODE_WORLD_WRITEABLE
				| Context.MODE_WORLD_READABLE);

		btnCpLeft.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				mLockPatternView.clearPattern();
				if (isPasswordRight) {

				}
			}
		});

		mLockPatternView
				.setOnPatternListener(new LockPatternView.OnPatternListener() {
					public void onPatternStart() {
						TopText.setText(R.string.pattern_start);
					}

					public void onPatternCleared() {
						Log.v("msg", "Cleared");
					}

					public void onPatternCellAdded(List<Cell> pattern) {
					}

					public void onPatternDetected(List<Cell> pattern) {
						String passwordTmp = AppIni.getString("Password", null);
						if (passwordTmp.equals(LockPatternUtils
								.patternToString(pattern))) {
							TopText.setText(null);
							isPasswordRight = true;
							mLockPatternView
									.setDisplayMode(LockPatternView.DisplayMode.Correct);
							ConfirmPasswordActivity.this.finish();
						} else {
							TopText.setText(R.string.input_wrong);
							mLockPatternView
									.setDisplayMode(LockPatternView.DisplayMode.Wrong);
						}
					}
				});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!packageName.isEmpty()) {
				mActivityManager.killBackgroundProcesses(packageName);
				Intent backhome = new Intent("android.intent.action.MAIN");
				backhome.addCategory("android.intent.category.HOME");
				backhome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(backhome);
			} else {
				ConfirmPasswordActivity.this.setResult(
						ProMgrActivity.RESULTFROMCONFIRMPASSWORD, i);
				ConfirmPasswordActivity.this.finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
