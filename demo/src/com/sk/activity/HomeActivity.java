package com.sk.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class HomeActivity extends TabActivity {
	
	TabHost tabHost;//����TabHost����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//ȡ��TabHost����
		tabHost = getTabHost();
	    
		/* ΪTabHost��ӱ�ǩ */
		//�½�һ��newTabSpec(newTabSpec)
		//�������ǩ��ͼ��(setIndicator)
		//��������(setContent)
	    tabHost.addTab(tabHost.newTabSpec("tab_test1")
	    		.setIndicator("�ҵĲ���", getResources().getDrawable(R.drawable.icon))
	    		.setContent(new Intent(this, Test.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab_test2")
	    		.setIndicator("�ҵĲ���", getResources().getDrawable(R.drawable.icon))
	    		.setContent(new Intent(this, Test.class)));
	    tabHost.addTab(tabHost.newTabSpec("tab_test4")
	    		.setIndicator("��������", getResources().getDrawable(R.drawable.icon))
	    		.setContent(new Intent(this, Test.class)
	    		.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        
	    
	    //���õ�ǰ��ʾ��һ����ǩ
	    tabHost.setCurrentTab(0);
	}

}