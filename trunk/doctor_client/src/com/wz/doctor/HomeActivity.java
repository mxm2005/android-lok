package com.wz.doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wz.doctor.handler.AdviceHandler;
import com.wz.doctor.handler.PatientListHandler;
import com.wz.doctor.handler.RecordHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HomeActivity extends Activity {
	public LinearLayout lin_summary;
	public LinearLayout lin_lv_tab;
	private ImageView imgGender;
	private ListView lv_titles;
	private PatientListHandler patientListHandler = null;
	private AdviceHandler adviceHandler = null;
	private RecordHandler recordHandler = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
		initView();
        patientListHandler = new PatientListHandler(this);
		adviceHandler = new AdviceHandler(this);
		recordHandler = new RecordHandler(this, lin_summary);
        lin_summary.removeAllViews();
		lin_summary.addView(patientListHandler.patientList());
		lin_lv_tab.removeAllViews();
		lin_lv_tab.addView(patientListHandler.patientSummary());
        imgGender = (ImageView) findViewById(R.id.imgGender);
        imgGender.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lin_summary.removeAllViews();
				lin_summary.addView(patientListHandler.patientList());
			}
		});
    }
    
    private LinearLayout recordSummary;
    
    private void initView() {
    	lin_lv_tab = (LinearLayout) findViewById(R.id.lin_lv_tab);
    	lin_summary = (LinearLayout) findViewById(R.id.lin_summary);
    	lv_titles = (ListView) findViewById(R.id.lv_titles);
    	SimpleAdapter adapter = new SimpleAdapter(this, getButtons(), R.layout.simple_list_item_activated_1, 
				new String[]{ "text1" }, 
				new int[]{ R.id.ibTitle });
    	lv_titles.setAdapter(adapter);
    	lv_titles.setSelector(R.drawable.btn_locallist_p);
    	lv_titles.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long position) {
				// TODO Auto-generated method stub
				switch ((int)position) {
				case 0:
					lin_summary.removeAllViews();
					lin_summary.addView(patientListHandler.patientList());
					lin_lv_tab.removeAllViews();
					lin_lv_tab.addView(patientListHandler.patientSummary());
					break;
				case 1:
					System.out.println("-0---");
					break;
				case 2:
					lin_summary.removeAllViews();
					lin_summary.addView(adviceHandler.adviceList());
					lin_lv_tab.removeAllViews();
					lin_lv_tab.addView(adviceHandler.adviceSummary());
					break;
				case 6:
//					lin_summary.removeAllViews();
//					lin_summary.addView(recordHandler.recordingDetail());
//					recordSummary = recordHandler.recordSummary();
					lin_lv_tab.removeAllViews();
					lin_lv_tab.addView(recordHandler.recordSummary());
//					recordSummary.findViewById(R.id.btn_record_add).setOnClickListener(new OnClickListener()
//					{
//						
//						@Override
//						public void onClick(View v)
//						{
//							lin_summary.removeAllViews();
//							lin_summary.addView(recordHandler.recordingDetail());
//						}
//					});
					break;
				}
			}
		});
    }
    
    /**
     * 左边列表功能按钮
     * @return
     */
	private List<? extends Map<String, ?>> getButtons() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text1", R.drawable.main_toolbar_homepage);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("text1", R.drawable.main_toolbar_pathway);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("text1", R.drawable.main_toolbar_report);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("text1", R.drawable.main_toolbar_advice);
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("text1", R.drawable.main_toolbar_memo);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("text1", R.drawable.main_toolbar_nurse);
		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("text1", R.drawable.main_toolbar_emr);
		lists.add(map);
		lists.add(map1);
		lists.add(map3);
		lists.add(map6);
		lists.add(map5);
		lists.add(map2);
		lists.add(map4);
		return lists;
	}
	
}