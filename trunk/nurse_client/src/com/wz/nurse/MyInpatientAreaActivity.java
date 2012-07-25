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
//    	for (int i = 0; i < 10; i++) {
    		Map<String, Object> map1 = new HashMap<String, Object>();
    		map1.put("number", "82");
    		map1.put("age", "妇产科病区");
    		lists.add(map1);
    		Map<String, Object> map2 = new HashMap<String, Object>();
    		map2.put("number", "83");
    		map2.put("age", "儿科病区");
    		lists.add(map2);
    		Map<String, Object> map3 = new HashMap<String, Object>();
    		map3.put("number", "91");
    		map3.put("age", "ICU");
    		lists.add(map3);
    		Map<String, Object> map4 = new HashMap<String, Object>();
    		map4.put("number", "124");
    		map4.put("age", "血透中心护理");
    		lists.add(map4);
    		Map<String, Object> map5 = new HashMap<String, Object>();
    		map5.put("number", "129");
    		map5.put("age", "新生儿科病区");
    		lists.add(map5);
    		Map<String, Object> map6 = new HashMap<String, Object>();
    		map6.put("number", "151");
    		map6.put("age", "综合内科病区");
    		lists.add(map6);
    		Map<String, Object> map7 = new HashMap<String, Object>();
    		map7.put("number", "152");
    		map7.put("age", "综合外科病区");
    		lists.add(map7);
    		Map<String, Object> map8 = new HashMap<String, Object>();
    		map8.put("number", "157");
    		map8.put("age", "急诊观察病区");
    		lists.add(map8);
//    	}
    	return lists;
	}
}
