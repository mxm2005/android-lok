package com.wz.doctor.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wz.doctor.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PatientAdapter extends BaseAdapter {
	private Context mContext;
	private List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();//用来存数据

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
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.patient_item, null);
			holder = new ViewHolder();
			holder.nurseGrade = (ImageView) convertView.findViewById(R.id.imgLevelName);
			holder.number = (TextView) convertView.findViewById(R.id.tvBedNO);
			holder.name = (TextView) convertView.findViewById(R.id.tvName);
			holder.hosNumber = (TextView) convertView.findViewById(R.id.tvHosNO);
			holder.age = (TextView) convertView.findViewById(R.id.tvAge);
			holder.gender = (TextView) convertView.findViewById(R.id.tvGender);
			holder.cos = (TextView) convertView.findViewById(R.id.tvCost);
			holder.time = (TextView) convertView.findViewById(R.id.tvTime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if ("一级护理".equals(lists.get(position).get("AAG02").toString())) {//同上
			holder.nurseGrade.setBackgroundResource(R.drawable.yjhl);
		} else if ("二级护理".equals(lists.get(position).get("AAG02").toString())) {
			holder.nurseGrade.setBackgroundResource(R.drawable.ejhl);
		} else if ("三级护理".equals(lists.get(position).get("AAG02").toString())) {
			holder.nurseGrade.setBackgroundResource(R.drawable.sjhl);
		} else if ("特级护理".equals(lists.get(position).get("AAG02").toString())) {
			holder.nurseGrade.setBackgroundResource(R.drawable.tjhl);
		} else if ("".equals(lists.get(position).get("AAG02").toString())) {
			holder.nurseGrade.setBackgroundDrawable(null);
		}
		holder.number.setText(lists.get(position).get("BCQ04B").toString());//序列号
		holder.name.setText(lists.get(position).get("VAA05").toString());//读取VAA05字段的为病人姓名
		holder.hosNumber.setText(lists.get(position).get("VAA04").toString());
		holder.age.setText(lists.get(position).get("Agep").toString());//读取Agep字段的为病人年龄
		holder.gender.setText(lists.get(position).get("ABW02").toString());
		holder.cos.setText(lists.get(position).get("ABJ02").toString());
		holder.time.setText(lists.get(position).get("VAE11").toString());
		return convertView;
	}
	
	static class ViewHolder {
		ImageView nurseGrade;//护理级别
		TextView number;//序列号
		TextView name;//病人姓名
		TextView hosNumber;
		TextView age;//年龄
		TextView gender;//性别
		TextView cos;//自费
		TextView time;//入院时间
	}
	
}
