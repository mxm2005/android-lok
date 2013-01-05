package com.wz.doctor.handler;

import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;

public class RecordHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_record_list;
	private LinearLayout record_sumarry;
	private ListView list_record;
	private ImageButton btn_record_add;
	
	public RecordHandler(HomeActivity mHomeActivity) {
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
		btn_record_add = (ImageButton) record_sumarry.findViewById(R.id.btn_record_add);
		return record_sumarry;
	}
	

	public LinearLayout recordDetail() {
		tab_record_list = (LinearLayout) layoutInflater.inflate(R.layout.tab_record_detail, null);
		return tab_record_list;
	}
	
}
