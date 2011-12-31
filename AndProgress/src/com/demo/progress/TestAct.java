package com.demo.progress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestAct extends Activity {
	private static final String TAG ="TestAct";
	public final static int REQUEST_CODE = 0x1;
	public final static int RESULT_CODE= 0x2 ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_field);
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
//		if(REQUEST_CODE == requestCode && RESULT_CODE == resultCode){
//			Log.d(TAG, data.getStringExtra("result"));
//		}
//	}
	
	public void MyOnClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			startActivityForResult(new Intent(TestAct.this, AndProgressActivity.class), REQUEST_CODE);
			break;
		default:
			break;
		}
	}
}
