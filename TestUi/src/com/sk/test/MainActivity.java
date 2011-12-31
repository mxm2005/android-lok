package com.sk.test;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static Boolean isExit = false;
    private static Boolean hasTask = false;
    Timer tExit = new Timer();
    TimerTask task;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        task = new TimerTask() {
             
            @Override
            public void run() {
                isExit = false;
                hasTask = true;
            }
        };
        
//        System.out.println("成功了？ " + getIcon("agree1"));
        
        EditText et = (EditText) findViewById(R.id.et);
		et.setKeyListener(new NumberKeyListener() {
			// 0无键盘 1英文键盘 2模拟键盘 3数字键盘
			@Override
			public int getInputType() {
				// TODO Auto-generated method stub
				return 8;
			}

			// 返回允许输入的字符
			@Override
			protected char[] getAcceptedChars() {
				// TODO Auto-generated method stub
				char[] c = { 'a', 'b', 'c', 'd', 'e', '1', '2' ,'3', '4', '5', '6', '7', '你'};
				return c;
			}
		});
    }
	
	private int getPirtrue(String type){
		Resources res = getResources();
		return res.getIdentifier(type, "drawable", getPackageName());
	}
	
	private int getIcon(String type){
		try {
			Field field = R.drawable.class.getField(type);
			int i = field.getInt(new R.drawable());
			System.out.println("i: " + i);
			return i;

		} catch (Exception e) {
			System.out.println("e: " + e.toString());
			return R.drawable.icon1;
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("TabHost_Index.java onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isExit == false ) {
                isExit = true;
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
                if(!hasTask) {
                    tExit.schedule(task, 2000);
                }
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}