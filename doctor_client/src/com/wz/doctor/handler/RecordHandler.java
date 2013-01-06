package com.wz.doctor.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;

public class RecordHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_record_list;
	private LinearLayout record_sumarry;
	private ListView list_record;
	private ImageButton btn_record_add;
	private SimpleAdapter mSimpleAdapter;
	private LinearLayout lin_summary;
	
	public RecordHandler(HomeActivity mHomeActivity, LinearLayout lin_summary) {
		this.lin_summary = lin_summary;
		this.mHomeActivity = mHomeActivity;
		layoutInflater = mHomeActivity.getLayoutInflater();
	}
	
	/**
	 * 左边summary
	 * @return
	 */
	public LinearLayout recordSummary() {
		record_sumarry = (LinearLayout) layoutInflater.inflate(R.layout.record_sumarry, null);
		list_record = (ListView) record_sumarry.findViewById(R.id.list_record);
		mSimpleAdapter = new SimpleAdapter(mHomeActivity, 
				getData(), 
				R.layout.list_record_summary, 
				new String[]{ "tv_name", "tv_size", "tv_date" }, 
				new int[]{ R.id.tv_name, R.id.tv_size, R.id.tv_date });
		list_record.setAdapter(mSimpleAdapter);
		list_record.setSelector(R.drawable.btn_locallist_p);
		btn_record_add = (ImageButton) record_sumarry.findViewById(R.id.btn_record_add);
		btn_record_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lin_summary.removeAllViews();
				lin_summary.addView(recordingDetail());
			}
		});
		return record_sumarry;
	}
	
	private Button btn_record_start_pause;
	
	public LinearLayout recordingDetail() {
		tab_record_list = (LinearLayout) layoutInflater.inflate(R.layout.tab_record_detail, null);
		btn_record_start_pause = (Button) tab_record_list.findViewById(R.id.btn_record_start_pause);
		btn_record_start_pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		return tab_record_list;
	}
	
	private List<? extends Map<String, ?>> getData() {
		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = null;
		maps = new HashMap<String, Object>();
		maps.put("tv_name", "我的备忘录1");
		maps.put("tv_size", "00:01");
		maps.put("tv_date", "2013-01-04 12:57:51");
		records.add(maps);
		return records;
	}
	
}
