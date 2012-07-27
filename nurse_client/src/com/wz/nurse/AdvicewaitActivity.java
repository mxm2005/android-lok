package com.wz.nurse;

import java.util.List;
import java.util.Map;

import com.wz.nurse.util.JSONUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
/**
 * 待执行界面
 * @author Administrator
 *
 */
public class AdvicewaitActivity extends Activity {
	private ListView lv_advice;
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advice_wait);
		initView();
	}
	
	//医嘱数据
	private List<Map<String, Object>> getAdviceDate() {
		JSONUtil ju = new JSONUtil();
		try {
			return ju.getData(getApplicationContext(), "doctors_advice.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void initView() {
		lv_advice = (ListView) findViewById(R.id.lv_advice);
		adapter = new SimpleAdapter(this, getAdviceDate(), R.layout.advice_item,
				new String[] { "VAF22" }, new int[] { R.id.tvAdvice });
		lv_advice.setAdapter(adapter);
		lv_advice.setOnItemClickListener(new OnItemClickListener() {//点击每个item的事件。

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = LayoutInflater.from(AdvicewaitActivity.this);
		        View textEntryView = inflater.inflate(R.layout.advice_dialog, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(AdvicewaitActivity.this);
		        builder.setView(textEntryView); 
		        AlertDialog ad = builder.create();
		        TextView VAF22N = (TextView) textEntryView.findViewById(R.id.VAF22N);
		        TextView FGross = (TextView) textEntryView.findViewById(R.id.FGross);
		        TextView RouteN = (TextView) textEntryView.findViewById(R.id.RouteN);
		        TextView VAF26 = (TextView) textEntryView.findViewById(R.id.VAF26);
		        TextView VAF36 = (TextView) textEntryView.findViewById(R.id.VAF36);
		        VAF22N.setText((String)getAdviceDate().get(3).get("VAF22N"));//显示相关的数据
		        FGross.setText((String)getAdviceDate().get(3).get("FGross"));
		        RouteN.setText((String)getAdviceDate().get(3).get("RouteN"));
		        VAF26.setText((String)getAdviceDate().get(3).get("VAF26"));
		        VAF36.setText("时间：" + (String)getAdviceDate().get(0).get("VAF36"));
		        ad.show();
			}
			
		});
	}
	
}
