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
    	for (int i = 0; i < 10; i++) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("tv_detailed", "1");
    		lists.add(map);
    	}
    	return lists;
    }
}
