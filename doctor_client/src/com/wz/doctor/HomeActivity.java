package com.wz.doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wz.doctor.util.JSONUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressWarnings("unused")
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
        patientList();//加载病人列表
//        adviceList();//加载医嘱
    }
    
    private void initView() {
    	layoutInflater = getLayoutInflater();
    	lin_summary = (LinearLayout) findViewById(R.id.lin_summary);
    }
    
	private void patientList() {
    	gv_patient= (GridView) layoutInflater.inflate(R.layout.tab_patient_list, null);
    	lin_summary.removeAllViews();
    	lin_summary.addView(gv_patient);
    	adapter = new SimpleAdapter(getApplicationContext(), 
    			getPatientData(), 
    			R.layout.patient_item, 
    			new String[] { "BCQ04B", "VAA05", "VAA04", "Agep", "ABW02", "ABJ02", "VAE11" }, 
				new int[] { R.id.tvBedNO, R.id.tvName, R.id.tvHosNO, R.id.tvAge, R.id.tvGender, R.id.tvCost, R.id.tvTime });
		gv_patient.setAdapter(adapter);
		gv_patient.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
    }
	
	/**
	 * 为PatientAdapter适配器提醒数据
	 * @return
	 */
    private List<Map<String, Object>> getPatientData() {
    	JSONUtil ju = new JSONUtil();//解析json类
    	List<Map<String, Object>> patients = new ArrayList<Map<String,Object>>();
    	Map<String, Object> maps = null;
    	try {
//			return ju.getData(getApplicationContext(), "patient_list.json");//解析我的病人这个json
    		List<Map<String, Object>> lists = ju.getData(getApplicationContext(), "patient_list.json");//解析我的病人这个json
    		for (Map<String, Object> map : lists) {
    			 maps = new HashMap<String, Object>();
//    			 maps.put("BCQO4B", map.get("BCQO4B"));
    			 System.out.println((String)map.get("BCQ04B") + "-----------");
    			 maps.put("BCQ04B", (String)map.get("BCQ04B"));
    			 maps.put("VAA05", (String)map.get("VAA05"));
    			 maps.put("VAA04", (String)map.get("VAA04"));
    			 maps.put("Agep", (String)map.get("Agep"));
    			 maps.put("ABW02", (String)map.get("ABW02"));
    			 maps.put("ABJ02", (String)map.get("ABJ02"));
    			 maps.put("VAE11", (String)map.get("VAE11"));
    			 patients.add(maps);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return patients;
    }
    
    private void adviceList() {
    	tab_advice_list = (LinearLayout) layoutInflater.inflate(R.layout.tab_advice_list, null);
    	lin_summary.removeAllViews();
    	lin_summary.addView(tab_advice_list);
    	ListView lv_advice = (ListView) tab_advice_list.findViewById(R.id.lv_advice);
    	SimpleAdapter sAdapter = new SimpleAdapter(getApplicationContext(), getData(), R.layout.advice_list_item, new String[]{"tvBedNO"}, new int[]{R.id.stock_change_percentage});
    	lv_advice.setAdapter(sAdapter);
    	lv_advice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    

	private List<? extends Map<String, ?>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		for (int i = 1; i < 50; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tvBedNO", i);
			lists.add(map);
		}
		return lists;
	}
}