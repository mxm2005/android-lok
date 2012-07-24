package com.wz.nurse.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wz.nurse.R;

/**
 * 我的病人适配器
 * @author yjh
 *
 */
public class PatientAdapter extends BaseAdapter {
	private Context mContext;
	private List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
	
	public PatientAdapter (Context context, List<Map<String, Object>> lists) {
		this.mContext = context;
		this.lists = lists;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.patient_item, null);
			holder = new ViewHolder();
			holder.number = (TextView) convertView.findViewById(R.id.tvBedNO);
			holder.name = (TextView) convertView.findViewById(R.id.tvName);
			holder.age = (TextView) convertView.findViewById(R.id.tvAge);
			holder.gender = (ImageView) convertView.findViewById(R.id.imgGender);
			holder.nurseGrade = (ImageView) convertView.findViewById(R.id.imgNurseGrade);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.number.setText(lists.get(position).get("number").toString());
		holder.name.setText(lists.get(position).get("name").toString());
		holder.age.setText(lists.get(position).get("age").toString());
		holder.gender.setBackgroundResource((Integer)lists.get(position).get("gender"));
		holder.nurseGrade.setBackgroundResource((Integer)lists.get(position).get("nurseGrade"));
		holder.nurseGrade.setOnClickListener(listener);
		return convertView;
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};

	static class ViewHolder {
		TextView number;
		TextView name;
		TextView age;
		ImageView gender;
		ImageView nurseGrade;
	}

}
