package com.wz.doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wz.doctor.util.JSONUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

//@SuppressWarnings("unused")
public class HomeActivity extends Activity {
	private GridView gv_patient;
	private SimpleAdapter adapter;
	private LinearLayout lin_summary;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_advice_list;
	private ImageView imgGender;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        initView();
//        patientList();//加载病人列表
        adviceList();//加载医嘱
        imgGender = (ImageView) findViewById(R.id.imgGender);
        imgGender.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				patientList();//加载病人列表
			}
		});
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
				patientItem();
			}
		});
    }
	
	private void patientItem() {
		LinearLayout patient_detailed = (LinearLayout) layoutInflater.inflate(R.layout.patient_detailed, null);
		ListView lv_detailed = (ListView) patient_detailed.findViewById(R.id.lv_detailed);
    	lin_summary.removeAllViews();
    	lin_summary.addView(patient_detailed);
    	adapter = new SimpleAdapter(this, getDetailedData(""),
				R.layout.detailed_item, new String[] { "tv_detailed" },
				new int[] { R.id.tv_detailed });
    	lv_detailed.setAdapter(adapter);
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
    	SimpleAdapter sAdapter = new SimpleAdapter(getApplicationContext(), 
    			getData(), 
    			R.layout.advice_list_item,
				new String[] { "NVAF11", "VAF45", "VAF22N", "VAF26", "BCE03A", "NBCK03" },
				new int[] { R.id.tvType, R.id.tvTime, R.id.tvDetail, R.id.tvUsage, R.id.tvPeople, R.id.tvOffices });
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
		JSONUtil ju = new JSONUtil();//解析json类
    	List<Map<String, Object>> advices = new ArrayList<Map<String,Object>>();
    	Map<String, Object> maps = null;
    	try {
    		List<Map<String, Object>> lists = ju.getData(getApplicationContext(), "doctors_advice.json");
    		for (Map<String, Object> map : lists) {
    			 maps = new HashMap<String, Object>();
    			 maps.put("NVAF11", (String)map.get("NVAF11"));
    			 maps.put("VAF45", (String)map.get("VAF45"));
    			 maps.put("VAF22N", (String)map.get("VAF22N"));
    			 maps.put("VAF26", (String)map.get("VAF26"));
    			 maps.put("BCE03A", (String)map.get("BCE03A"));
    			 maps.put("NBCK03", (String)map.get("NBCK03"));
    			 advices.add(maps);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return advices;
	}
	
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