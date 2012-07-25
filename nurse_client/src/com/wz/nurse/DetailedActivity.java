package com.wz.nurse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DetailedActivity extends Activity {
	private EditText et_number;
	private ListView lv_detailed;
	private SimpleAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_detailed);
        initView();
    }
    
    private void initView() {
    	et_number = (EditText) findViewById(R.id.et_number);
    	lv_detailed = (ListView) findViewById(R.id.lv_detailed);
		adapter = new SimpleAdapter(this, getDetailedData(),
				R.layout.detailed_item, new String[] { "tv_detailed" },
				new int[] { R.id.tv_detailed });
		lv_detailed.setAdapter(adapter);
    }

	private List<Map<String, Object>> getDetailedData() {
    	List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("tv_detailed", "姓名：徐桂芬");
		lists.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("tv_detailed", "性别：女");
		lists.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("tv_detailed", "年龄：54岁");
		lists.add(map3);
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("tv_detailed", "床位：401-24");
		lists.add(map4);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("tv_detailed", "住院医生：谢晓月");
		lists.add(map5);
		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("tv_detailed", "科室：住院肾科");
		lists.add(map6);
		Map<String, Object> map7 = new HashMap<String, Object>();
		map7.put("tv_detailed", "护理级别:II级护理");
		lists.add(map7);
    	return lists;
    }
}
