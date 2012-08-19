package com.wz.doctor.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;
import com.wz.doctor.adapter.PatientAdapter;
import com.wz.doctor.util.JSONUtil;

public class PatientListHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;
	private GridView gv_patient;
	private LinearLayout patient_sumarry;
	private LinearLayout lin_patient_all;//所有病人
	private LinearLayout lin_patient_my;//我的病人
	private ImageButton cb_all;
	private ImageButton cb_my;
	
	public PatientListHandler(HomeActivity mHomeActivity) {
		this.mHomeActivity = mHomeActivity;
		layoutInflater = mHomeActivity.getLayoutInflater();
	}
	
	/**
	 * 左边summary
	 * @return
	 */
	public LinearLayout patientSummary() {
		patient_sumarry = (LinearLayout) layoutInflater.inflate(R.layout.patient_sumarry, null);
		lin_patient_all = (LinearLayout) patient_sumarry.findViewById(R.id.lin_patient_all);
		lin_patient_my = (LinearLayout) patient_sumarry.findViewById(R.id.lin_patient_my);
		cb_all = (ImageButton) patient_sumarry.findViewById(R.id.cb_all);
		cb_my = (ImageButton) patient_sumarry.findViewById(R.id.cb_my);
		lin_patient_all.setOnClickListener(buttonListener);
		lin_patient_my.setOnClickListener(buttonListener);
		return patient_sumarry;
	}
	
	private OnClickListener buttonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.lin_patient_all:
				lin_patient_all.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_patient_my.setBackgroundDrawable(null);
				cb_all.setVisibility(View.VISIBLE);
				cb_my.setVisibility(View.INVISIBLE);
				break;

			case R.id.lin_patient_my:
				lin_patient_my.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_patient_all.setBackgroundDrawable(null);
				cb_my.setVisibility(View.VISIBLE);
				cb_all.setVisibility(View.INVISIBLE);
				break;
			}
		}
	};
	
	/**
	 * 加载所有病人列表
	 */
	public GridView patientList() {
    	gv_patient= (GridView) layoutInflater.inflate(R.layout.tab_patient_list, null);
//    	lin_summary.removeAllViews();
//    	lin_summary.addView(gv_patient);
    	PatientAdapter pAdapter = new PatientAdapter(mHomeActivity, getPatientData());
//    	adapter = new SimpleAdapter(getApplicationContext(), 
//    			getPatientData(), 
//    			R.layout.patient_item, 
//    			new String[] { "BCQ04B", "VAA05", "VAA04", "Agep", "ABW02", "ABJ02", "VAE11" }, 
//				new int[] { R.id.tvBedNO, R.id.tvName, R.id.tvHosNO, R.id.tvAge, R.id.tvGender, R.id.tvCost, R.id.tvTime });
		gv_patient.setAdapter(pAdapter);
		gv_patient.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				DetailedPatientHandler detailedHandler = new DetailedPatientHandler(mHomeActivity);
				mHomeActivity.lin_summary.removeAllViews();
				mHomeActivity.lin_summary.addView(detailedHandler.patientItem());
			}
		});
		return gv_patient;
    }
	
	/**
	 * 为病人列表适配器数据
	 * @return
	 */
    private List<Map<String, Object>> getPatientData() {
    	JSONUtil ju = new JSONUtil();//解析json类
    	List<Map<String, Object>> patients = new ArrayList<Map<String,Object>>();
    	Map<String, Object> maps = null;
    	try {
    		List<Map<String, Object>> lists = ju.getData(mHomeActivity, "patient_list.json");//解析我的病人这个json
    		for (Map<String, Object> map : lists) {
    			 maps = new HashMap<String, Object>();
//    			 maps.put("BCQO4B", map.get("BCQO4B"));
    			 System.out.println((String)map.get("Agep") + "-----------");
    			 maps.put("BCQ04B", (String)map.get("BCQ04B"));
    			 maps.put("VAA05", (String)map.get("VAA05"));
    			 maps.put("VAA04", (String)map.get("VAA04"));
    			 maps.put("Agep", (String)map.get("Agep"));
    			 maps.put("ABW02", (String)map.get("ABW02"));
    			 maps.put("ABJ02", (String)map.get("ABJ02"));
    			 maps.put("VAE11", (String)map.get("VAE11"));
    			 maps.put("AAG02", (String)map.get("AAG02"));
    			 patients.add(maps);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return patients;
    }
}
