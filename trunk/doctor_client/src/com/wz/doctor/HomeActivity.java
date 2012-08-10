package com.wz.doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class HomeActivity extends Activity {
	private GridView gv_patient;
	private SimpleAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        gv_patient = (GridView) findViewById(R.id.gv_patient);
		adapter = new SimpleAdapter(getApplicationContext(), getDate(), R.layout.patient_item, new String[]{"tvBedNO"}, new int[]{R.id.tvBedNO});
		gv_patient.setAdapter(adapter);
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