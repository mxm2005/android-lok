package com.wz.doctor.widget;

import com.wz.doctor.MyFragmentActivity.DetailsActivity;
import com.wz.doctor.R;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitleFragment extends ListFragment {
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
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.simple_list_item_activated_1, TITLES));
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
}
