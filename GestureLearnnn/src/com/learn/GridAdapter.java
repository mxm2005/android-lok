package com.learn;

import android.content.Context;
import android.gesture.GestureLibrary;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	// 每个gridItem显示的值
	private Context mContext;
	private LayoutInflater mInflater;

	public GridAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		// 返回适配器中数据的数量
		return ListGestures.pics.length;
	}

	public Object getItem(int position) {
		// 用不到
		return null;
	}

	public long getItemId(int position) {
		// 用不到
		return 0;
	}

	// 此方法的convertView是在grid_item里定义的组件。这里是一个ImageView和TextView
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.grid_item, null);
		}
		ImageView icon = (ImageView) convertView.findViewById(R.id.itemIcon);
		TextView text = (TextView) convertView.findViewById(R.id.itemText);
		icon.setImageBitmap(ListGestures.pics[position]);
		text.setText(ListGestures.gesName[position]);
		return convertView;// 返回已经改变过的convertView，节省系统资源
	}

}
