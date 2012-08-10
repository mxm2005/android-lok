package com.wz.doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

public class HomeActivity extends Activity {
	private GridView gv_patient;
	private SimpleAdapter adapter;
	private LinearLayout lin_summary;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_advice_list;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        initView();
//        patientList();//加载病人列表
        adviceList();//加载医嘱
    }
    
    private void initView() {
    	layoutInflater = getLayoutInflater();
    	lin_summary = (LinearLayout) findViewById(R.id.lin_summary);
    }
    
    private void patientList() {
    	gv_patient= (GridView) layoutInflater.inflate(R.layout.tab_patient_list, null);
    	lin_summary.removeAllViews();
    	lin_summary.addView(gv_patient);
    	adapter = new SimpleAdapter(getApplicationContext(), getDate(), R.layout.patient_item, new String[]{"tvBedNO"}, new int[]{R.id.tvBedNO});
		gv_patient.setAdapter(adapter);
    }
    
    private void adviceList() {
    	tab_advice_list = (LinearLayout) layoutInflater.inflate(R.layout.tab_advice_list, null);
    	lin_summary.removeAllViews();
    	lin_summary.addView(tab_advice_list);
    }
    
    private List<? extends Map<String, ?>> getDate() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		for (int i = 1; i < 26; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tvBedNO", i);
			lists.add(map);
		}
		return lists;
	}
}