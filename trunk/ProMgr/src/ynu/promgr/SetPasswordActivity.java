package ynu.promgr;

import java.util.ArrayList;
import java.util.List;
import ynu.util.LockPatternUtils;
import ynu.widget.LockPatternView;
import ynu.widget.LockPatternView.Cell;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SetPasswordActivity extends Activity {

	private SharedPreferences AppIni;
	private String password;

	private Intent i;
	private Button btnSpLeft, btnSpRight;
	private TextView TopText, BottomText;
	private LockPatternView mLockPatternView;
	private ArrayList<Cell> mPattern;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setpassword);

		btnSpLeft = (Button) findViewById(R.id.btnSpLeft);
		btnSpRight = (Button) findViewById(R.id.btnSpRight);
		TopText = (TextView) findViewById(R.id.SpTopText);
		BottomText = (TextView) findViewById(R.id.SpBottomText);
		mLockPatternView = (LockPatternView) findViewById(R.id.SpLockPattern);

		AppIni = getSharedPreferences("configure", Context.MODE_WORLD_WRITEABLE
				| Context.MODE_WORLD_READABLE);
		boolean FirstTimeOpenApp = AppIni.getBoolean("FirstTimeOpenApp", true);// 记录是否为第一次进入应用程序
		if (FirstTimeOpenApp) {
			btnSpLeft.setText(R.string.ok);
			btnSpRight.setText(R.string.exit);
			TopText.setText(R.string.firsttime);
			BottomText.setText(R.string.welcome);
		}

		btnSpLeft.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				Log.v("msg", "jiasuo:" + password);
				AppIni.edit().putString("Password", password).commit();
				Log.v("msg", "jiesuo:" + AppIni.getString("Password", null));
				AppIni.edit().putBoolean("FirstTimeOpenApp", false).commit();
				SetPasswordActivity.this.finish();
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
						if (pattern.size() >= 4) {
							TopText.setText(R.string.pattern_end);
							mLockPatternView
									.setDisplayMode(LockPatternView.DisplayMode.Correct);
							mPattern = (ArrayList<Cell>) pattern;
							password = LockPatternUtils
									.patternToString(pattern);
						} else {
							TopText.setText(R.string.patternlimit);
							mLockPatternView
									.setDisplayMode(LockPatternView.DisplayMode.Wrong);
						}
					}
				});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean FirstTimeOpenApp = AppIni.getBoolean("FirstTimeOpenApp",
					true);// 记录是否为第一次进入应用程序
			if (FirstTimeOpenApp) {
				AppIni.edit().putBoolean("FirstTimeOpenApp", true).commit();
				i = SetPasswordActivity.this.getIntent();
				SetPasswordActivity.this.setResult(
						ProMgrActivity.RESULTFROMSETPASSWORD, i);
				SetPasswordActivity.this.finish();
			} else {
				SetPasswordActivity.this.finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
