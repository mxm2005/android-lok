package com.wz.nurse;

import android.app.Activity;
import android.os.Bundle;
/**
 * 点击其他病区或更多时跳转到这个界面，提示待开发界面
 * @author Administrator
 *
 */
public class FrontierActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_frontier);
	}
}
