package com.wz.nurse;

import java.util.List;
import java.util.Map;

import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
				LayoutInflater inflater = LayoutInflater.from(AdvicewaitActivity.this);
		        View textEntryView = inflater.inflate(R.layout.advice_dialog, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(AdvicewaitActivity.this);
		        builder.setView(textEntryView); //关键
		        AlertDialog ad = builder.create();
		        TextView VAF22N = (TextView) textEntryView.findViewById(R.id.VAF22N);
		        TextView FGross = (TextView) textEntryView.findViewById(R.id.FGross);
		        TextView RouteN = (TextView) textEntryView.findViewById(R.id.RouteN);
		        TextView VAF26 = (TextView) textEntryView.findViewById(R.id.VAF26);
		        TextView VAF36 = (TextView) textEntryView.findViewById(R.id.VAF36);
		        VAF22N.setText((String)getData().get(3).get("VAF22N"));
		        FGross.setText((String)getData().get(3).get("FGross"));
		        RouteN.setText((String)getData().get(3).get("RouteN"));
		        VAF26.setText((String)getData().get(3).get("VAF26"));
		        VAF36.setText("时间：" + (String)getData().get(0).get("VAF36"));
		        ad.show();
			}
		});
	}
	
}
