package com.wz.nurse;

import java.util.List;
import java.util.Map;

import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdvicewaitActivity extends Activity {
	private Button btn_advice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advice_wait);
		initView();
	}
	
	private List<Map<String, Object>> getData() {
		JSONUtil ju = new JSONUtil();
		try {
			return ju.getData(getApplicationContext(), "doctors_advice.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void initView() {
		btn_advice = (Button) findViewById(R.id.btn_advice);
		btn_advice.setText((String)getData().get(1).get("VAF22"));
		btn_advice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
