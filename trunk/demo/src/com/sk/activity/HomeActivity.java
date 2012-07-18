package com.sk.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class HomeActivity extends TabActivity {
	
	TabHost tabHost;//声明TabHost对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//取得TabHost对象
		tabHost = getTabHost();
	    
		/* 为TabHost添加标签 */
		//新建一个newTabSpec(newTabSpec)
		//设置其标签和图标(setIndicator)
		//设置内容(setContent)
	    tabHost.addTab(tabHost.newTabSpec("tab_test1")
	    		.setIndicator("我的病人", getResources().getDrawable(R.drawable.icon))
	    		.setContent(new Intent(this, Test.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab_test2")
	    		.setIndicator("我的病区", getResources().getDrawable(R.drawable.icon))
	    		.setContent(new Intent(this, Test.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab_test4")
	    		.setIndicator("其他病区", getResources().getDrawable(R.drawable.icon))
	    		.setContent(new Intent(this, Test.class)
	    		.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        
	    
	    //设置当前显示哪一个标签
	    tabHost.setCurrentTab(0);
	}

}