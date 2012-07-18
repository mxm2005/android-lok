package com.sk.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Test extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tx = new TextView(this);
		tx.setText("wqni");
		setContentView(tx);
	}
}
