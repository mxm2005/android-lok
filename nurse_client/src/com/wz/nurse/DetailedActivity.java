package com.wz.nurse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DetailedActivity extends Activity {
//	private EditText et_number;
	private ListView lv_detailed;
	private SimpleAdapter adapter;
	private Button btn_back;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_detailed);
        initView();
    }
    
    private void initView() {
    	Intent intent = getIntent();
    	String name = intent.getStringExtra("name");
//    	et_number = (EditText) findViewById(R.id.et_number);
    	lv_detailed = (ListView) findViewById(R.id.lv_detailed);
		adapter = new SimpleAdapter(this, getDetailedData(name),
				R.layout.detailed_item, new String[] { "tv_detailed" },
				new int[] { R.id.tv_detailed });
		lv_detailed.setAdapter(adapter);
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(buttonListener);
    }
    
    private OnClickListener buttonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = null;
			switch (v.getId()) {
			case R.id.btn_back:
				DetailedActivity.this.setResult(RESULT_OK, intent);
				DetailedActivity.this.finish();
				break;
			}
		}
	};

	@SuppressWarnings("rawtypes")
	private List<Map<String, Object>> getDetailedData(String name) {
		JSONUtil ju = new JSONUtil();
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		try {
			List<Map<String, Object>> patientList = ju.getData(getApplicationContext(), "patient_item.json");
			for (Map<String, Object> pl : patientList) {
				Iterator iter = pl.entrySet().iterator(); 
				while (iter.hasNext()) {
					map = new HashMap<String, Object>();
				    Map.Entry entry = (Map.Entry) iter.next(); 
				    String key = (String) entry.getKey();
//				    if ("VAA05".equals(key)) {
//				    	if (name.equals((String)entry.getValue())) {
				    		if ("VAA05".equals(key)) {
						    	map.put("tv_detailed", "姓名：" + entry.getValue());
						    	lists.add(map);
						    } else if ("ABW02".equals(key)) {
						    	map.put("tv_detailed", "性别：" + entry.getValue());
						    	lists.add(map);
						    } else if ("Agep".equals(key)) {
						    	map.put("tv_detailed", "年龄：" + entry.getValue());
						    	lists.add(map);
						    } else if ("BCQ04B".equals(key)) {
						    	map.put("tv_detailed", "床号：" + entry.getValue());
						    	lists.add(map);
						    } else if ("AAG02".equals(key)) {
						    	map.put("tv_detailed", "级别：" + entry.getValue());
						    	lists.add(map);
						    } else if ("BCE03C".equals(key)) {
						    	map.put("tv_detailed", "住院医生：" + entry.getValue());
						    	lists.add(map);
						    } else if ("BCK03".equals(key)) {
						    	map.put("tv_detailed", "住院科室：" + entry.getValue());
						    	lists.add(map);
						    } else if ("VBM04".equals(key)) {
						    	map.put("tv_detailed", "病人余额：" + entry.getValue());
						    	lists.add(map);
						    } else if ("ABC02".equals(key)) {
						    	map.put("tv_detailed", "病人费用：" + entry.getValue());
						    	lists.add(map);
						    }
//				    	}
//				    }
				}
			}
			return lists;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
}
