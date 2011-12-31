package com.android.CustomDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CustomDialogUsage extends Activity {
	CustomDialog cd;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        cd = new CustomDialog(this);
        
        
        Button buttonYes = (Button) findViewById(R.id.main_button);
        buttonYes.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
		        cd.show();
				
			}
        });

    }
    
}