package com.wz.nurse;

import java.util.List;
import java.util.Map;

import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AdviceSkinTestActivity extends Activity {
	private ListView lv_advice;
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advice_wait);
		initView();
	}
	
	private List<Map<String, Object>> getAdviceDate() {
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
		lv_advice = (ListView) findViewById(R.id.lv_advice);
		adapter = new SimpleAdapter(this, getAdviceDate(), R.layout.advice_item,
				new String[] { "VAF22" }, new int[] { R.id.tvAdvice });
		lv_advice.setAdapter(adapter);
		lv_advice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(AdviceSkinTestActivity.this)
				.setTitle("皮试观察")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setSingleChoiceItems(
						new String[] { "阴性", "阳性" }, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).setNegativeButton("取消", null).show();
			}
			
		});
	}
	
}
