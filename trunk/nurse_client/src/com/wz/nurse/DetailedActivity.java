package com.wz.nurse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class DetailedActivity extends Activity {
	private EditText et_number;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_detailed);
        initView();
    }
    
    private void initView() {
    	et_number = (EditText) findViewById(R.id.et_number);
//    	et_number.setCompoundDrawables(getResources().getDrawable(android.R.drawable.ic_menu_search), null, null, null);
    }
}
