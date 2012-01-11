package com.apkstory.activity;

import com.apkstory.R;
import com.apkstory.service.TopFloatService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 悬浮窗的制作
 * @author 锋翼
 * @link www.apkstory.com
 *
 */
public class TopFloatMain extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent service = new Intent();
		service.setClass(this, TopFloatService.class);		
		startService(service);//启动服务
    }
    
}