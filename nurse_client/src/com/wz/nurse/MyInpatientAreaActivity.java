package com.wz.nurse;

import java.util.List;
import java.util.Map;

import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyInpatientAreaActivity extends Activity {
	private SimpleAdapter adapter;
	private ListView lv_area;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_inpatient_area);
		lv_area = (ListView) findViewById(R.id.lv_area);
		adapter = new SimpleAdapter(this, getAreaDate(), R.layout.area_item, new String[]{ "BCK01", "BCK03" }, new int[] { R.id.tvNumber, R.id.tvArea });
		lv_area.setAdapter(adapter);
	}

	private List<? extends Map<String, ?>> getAreaDate() {
		JSONUtil ju = new JSONUtil();
		try {
			List<Map<String, Object>> lists = ju.getData(getApplicationContext(), "patient_area.json");
			return lists;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
