package com.genius.demo;

import com.genius.demo.clock.CustomClock;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CustomClockDemoActivity extends Activity {
    /** Called when the activity is first created. */
    
	
//	private CustomClock		mCustomClock1;
//	private CustomClock		mCustomClock2;
//	private CustomClock		mCustomClock3;
//	private CustomClock		mCustomClock4;
	
	private CustomClock		mCustomClock1;
	private CustomClock		mCustomClock2;
	private CustomClock		mCustomClock3;
	
	
	private Button 			mBtnSetting;
	private Button 			mBtnSave;
	private Button 			mBtnNoSave;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        init();
    }
    
    
    
    
    
    public void init()
    {
    	
    	mCustomClock1 = (CustomClock) findViewById(R.id.clock1);
    	mCustomClock1.switchClockState(CustomClock._ClockState.eQ_CLOCK_RUN);
    	
    	mCustomClock2 = (CustomClock) findViewById(R.id.clock2);
    	mCustomClock2.switchClockState(CustomClock._ClockState.eQ_CLOCK_RUN);
    	
    	mCustomClock3 = (CustomClock) findViewById(R.id.clock3);
    	mCustomClock3.switchClockState(CustomClock._ClockState.eQ_CLOCK_RUN);
    	
    	mBtnSetting = (Button) findViewById(R.id.buttonSetting);
    	mBtnSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Setting();
			}
		});
    	
    	mBtnSave = (Button) findViewById(R.id.buttonSave);
    	mBtnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Save();
			}
		});
    	
    	mBtnNoSave = (Button) findViewById(R.id.buttonNoSave);
    	mBtnNoSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				noSave();
			}
		});
   	
//    	mCustomClock1.setVisibility(View.INVISIBLE);
    	mCustomClock2.setVisibility(View.GONE);
    	mCustomClock3.setVisibility(View.GONE);
   	
    }
    
    
    public void Setting()
    {
    	mCustomClock1.switchClockState(CustomClock._ClockState.eQ_CLOCK_SETTING);
    }
    
    public void Save()
    {
    	mCustomClock1.saveTimeToSystem();
    	mCustomClock1.switchClockState(CustomClock._ClockState.eQ_CLOCK_RUN);
    }
    
    public void noSave()
    {
    	mCustomClock1.reviseTime();
    	mCustomClock1.switchClockState(CustomClock._ClockState.eQ_CLOCK_RUN);
    }
    

    
}