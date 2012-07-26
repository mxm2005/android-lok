package com.wz.nurse;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class NurseRecordActivity extends Activity {
	private LinearLayout lin_record;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nurse_record);
        initView();
    }
	
	private void initView() {
		lin_record = (LinearLayout) findViewById(R.id.lin_record);
		lin_record.removeAllViews();
		View view = LayoutInflater.from(this).inflate(R.layout.nurse_info, null);
		lin_record.addView(view);
	}
}
