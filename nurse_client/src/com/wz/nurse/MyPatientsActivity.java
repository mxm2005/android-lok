package com.wz.nurse;

import java.util.List;
import java.util.Map;

import com.wz.nurse.adapter.PatientAdapter;
import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MyPatientsActivity extends Activity {
	private GridView gv_patient;
	private PatientAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_mypatient);
        initView();
    }
    
    private OnItemClickListener gvListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MyPatientsActivity.this, DetailedActivity.class);
			intent.putExtra("name", "郑孝平");
			startActivity(intent);
		}
	};
    
    private List<Map<String, Object>> getPatientData() {
//    	List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
//    	for (int i = 0; i < 10; i++) {
//    		Map<String, Object> map = new HashMap<String, Object>();
//    		map.put("number", "1");
//    		map.put("age", "23岁");
//    		map.put("name", "李明");
//    		map.put("gender", R.drawable.female);
//    		map.put("nurseGrade", R.drawable.yjhl);
//    		lists.add(map);
//    	}
//    	return lists;
    	JSONUtil ju = new JSONUtil();
    	try {
			return ju.getData(getApplicationContext(), "patient_list.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    private void initView() {
    	gv_patient = (GridView) findViewById(R.id.gv_patient);
    	adapter = new PatientAdapter(getApplicationContext(), getPatientData());
    	gv_patient.setAdapter(adapter);
    	gv_patient.setOnItemClickListener(gvListener);
    }
}
