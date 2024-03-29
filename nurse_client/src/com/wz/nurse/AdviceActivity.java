package com.wz.nurse;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * 医嘱界面
 * @author Administrator
 *
 */
public class AdviceActivity extends TabActivity {
	private TabHost tabHost;
	private TabWidget tabWidget;
	private Button btn_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctors_advice);
		initView();
	}
	
	
	//初始化view，加入“待执行”“皮试观察”两个选项卡
	private void initView() {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("tab_test1")
	    		.setIndicator("待执行", null).setContent(new Intent(this, AdvicewaitActivity.class)));//点击待执行选项卡跳转到AdvicewaitActivity界面
	    tabHost.addTab(tabHost.newTabSpec("tab_test2")
	    		.setIndicator("皮试观察", null).setContent(new Intent(this, AdviceSkinTestActivity.class)//点击皮试观察选项卡跳转到AdviceSkinTestActivity界面
	    		.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
	    
		tabWidget = tabHost.getTabWidget();
		for (int i = 0; i < tabWidget.getChildCount(); i++) {//改变原生控件选项卡的高度。
			tabWidget.getChildAt(i).getLayoutParams().height = 45;
			tabWidget.getChildAt(i).getLayoutParams().width = 65;
			TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
			tv.setTextSize(15);
			tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
		}
	    
		// 改变头部的背景色
//		for (int i = 0; i < tabWidget.getChildCount(); i++) {
//			Object v = tabWidget.getChildAt(i);
//			if (v instanceof RelativeLayout) {
//				RelativeLayout vvv = (RelativeLayout) v;
//				if (tabHost.getCurrentTab() == i) {
//					vvv.setBackgroundColor(R.color.sel_tab);
//				} else {
//					vvv.setBackgroundColor(R.color.nor_tab);
//				}
//				
//			}
//		}
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {//返回按钮事件
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = null;
				AdviceActivity.this.setResult(RESULT_OK, intent);
				AdviceActivity.this.finish();
			}
		});
	}
}
