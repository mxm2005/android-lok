package com.wz.doctor;

import com.wz.doctor.widget.DetailsFragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.GridView;

public class MyFragmentActivity extends Activity {
	private GridView gv_patient;// 九宫图控件

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_layout);
	}

	public class DetailsActivity extends Activity {
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				finish();
				return;
			}
			if (savedInstanceState == null) {
				DetailsFragment df = new DetailsFragment();
				df.setArguments(getIntent().getExtras());
				getFragmentManager().beginTransaction().add(
						android.R.id.content, df);
			}
		}
	}
}