package com.wz.doctor.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.wz.doctor.AddAdviceActivity;
import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;
import com.wz.doctor.util.JSONUtil;

public class AdviceHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_advice_list;
	private LinearLayout advice_sumarry;

	public AdviceHandler(HomeActivity mHomeActivity) {
		this.mHomeActivity = mHomeActivity;
		layoutInflater = mHomeActivity.getLayoutInflater();
	}
	
	/**
	 * 左边summary
	 * @return
	 */
	public LinearLayout adviceSummary() {
		advice_sumarry = (LinearLayout) layoutInflater.inflate(R.layout.advice_sumarry, null);
		return advice_sumarry;
	}

	/**
	 * 加载医嘱界面
	 */
	public LinearLayout adviceList() {
		tab_advice_list = (LinearLayout) layoutInflater.inflate(
				R.layout.tab_advice_list, null);
		ListView lv_advice = (ListView) tab_advice_list
				.findViewById(R.id.lv_advice);
		SimpleAdapter sAdapter = new SimpleAdapter(mHomeActivity, getData(),
				R.layout.advice_list_item, new String[] { "NVAF11", "VAF45",
						"VAF22N", "VAF26", "BCE03A", "NBCK03" }, new int[] {
						R.id.tvType, R.id.tvTime, R.id.tvDetail, R.id.tvUsage,
						R.id.tvPeople, R.id.tvOffices });
		lv_advice.setAdapter(sAdapter);
		lv_advice.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		lv_advice.setSelector(R.drawable.btn_locallist_p);
		Button btn_add = (Button) tab_advice_list.findViewById(R.id.btn_add);
		btn_add.setOnClickListener(buttonListener);
		Button btn_del = (Button) tab_advice_list.findViewById(R.id.btn_del);
		btn_del.setOnClickListener(buttonListener);
		return tab_advice_list;
	}

	private List<? extends Map<String, ?>> getData() {
		JSONUtil ju = new JSONUtil();// 解析json类
		List<Map<String, Object>> advices = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = null;
		try {
			List<Map<String, Object>> lists = ju.getData(mHomeActivity,
					"doctors_advice.json");
			for (Map<String, Object> map : lists) {
				maps = new HashMap<String, Object>();
				maps.put("NVAF11", (String) map.get("NVAF11"));
				maps.put("VAF45", (String) map.get("VAF45"));
				maps.put("VAF22N", (String) map.get("VAF22N"));
				maps.put("VAF26", (String) map.get("VAF26"));
				maps.put("BCE03A", (String) map.get("BCE03A"));
				maps.put("NBCK03", (String) map.get("NBCK03"));
				advices.add(maps);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return advices;
	}

	private OnClickListener buttonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_add:
				Intent intent = new Intent(mHomeActivity,
						AddAdviceActivity.class);
				mHomeActivity.startActivity(intent);
				break;
			case R.id.btn_del:
				dialog();
				break;
			}
		}
	};
	
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(mHomeActivity);
		builder.setMessage("确认要删除吗？");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}
