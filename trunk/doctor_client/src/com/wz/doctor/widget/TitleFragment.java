/*package com.wz.doctor.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wz.doctor.MyFragmentActivity.DetailsActivity;
import com.wz.doctor.R;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TitleFragment extends ListFragment implements OnItemClickListener {
	boolean mDualPane; // 检验是否有DetailFragment，没有就另起一个activity
	int mCurCheckPosition;

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curPosition", mCurCheckPosition);
	}

	@Override
	// 初始化TitleFragment
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		ArrayAdapter<String> a = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item_activated_1, TITLES);
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.simple_list_item_activated_1, 
				new String[]{ "text1" }, 
				new int[]{ android.R.id.text1 });
		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println("00000000000000000000");
			}
		});
		View detailsFrame = getActivity().findViewById(R.id.details);
		mDualPane = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;
		if (savedInstanceState != null) {
			mCurCheckPosition = savedInstanceState.getInt("curPosition", 0);
		}
		if (mDualPane) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(mCurCheckPosition);
		}
	}

	private List<? extends Map<String, ?>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("text1", R.drawable.main_toolbar_homepage);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("text1", R.drawable.main_toolbar_pathway);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("text1", R.drawable.main_toolbar_report);
		lists.add(map);
		lists.add(map1);
		lists.add(map2);
		return lists;
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	// 显示选中Item对于的内容
	private void showDetails(int position) {
		int index = position;
		// 如果有是平板，有DetailFragment，就执行以下代码
		if (mDualPane) {
			getListView().setItemChecked(index, true);
			DetailsFragment df = (DetailsFragment) getFragmentManager()
					.findFragmentById(R.id.details);
			if (df == null || df.getShowIndex() != index) {
				df = DetailsFragment.newInstance(index);
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.details, df);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		}
		// 如果是手机，没有Fragment，就另起一个Activity
		else {
			Intent i = new Intent();
			i.setClass(getActivity(), DetailsActivity.class);
			i.putExtra("index", index);
			startActivity(i);
		}
	}

	public static final String[] TITLES = { "病人列表", "医嘱",
			"Henry VIII", "Richard II", "Richard III", "Merchant of Venice",
			"Othello", "King Lear" };

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		System.out.println("--------------");
	}
}
*/