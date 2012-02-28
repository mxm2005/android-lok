package com.c35.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityToast extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitytoast);
//		setTitle("");
		Button button = (Button) findViewById(R.id.disbutton);
		button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ActivityToast.this.finish();
			}
		});
		
	}

}
