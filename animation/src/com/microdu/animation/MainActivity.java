package com.microdu.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, OtherActivity.class);
		        startActivity(intent);
		        overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
			}
		});
        
        
    }
}