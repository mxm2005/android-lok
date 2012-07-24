package com.wz.nurse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		adapter = new SimpleAdapter(this, getAreaDate(), R.layout.area_item, new String[]{ "number", "age" }, new int[] { R.id.tvNumber, R.id.tvArea });
		lv_area.setAdapter(adapter);
	}

	private List<? extends Map<String, ?>> getAreaDate() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
    	for (int i = 0; i < 10; i++) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("number", "1");
    		map.put("age", "23岁");
    		map.put("name", "李明");
    		map.put("gender", R.drawable.female);
    		map.put("nurseGrade", R.drawable.yjhl);
    		lists.add(map);
    	}
    	return lists;
	}
}
