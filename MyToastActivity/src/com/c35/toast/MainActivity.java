package com.c35.toast;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private MyToast my =null;
    Button toastgoButton,activitygoButton,backButton;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//用于定时的线程
		new Thread(new Runnable() {
			public void run() {
//				// TODO Auto-generated method stub
				long current = System.currentTimeMillis();
		        Log.d(TAG, "current = "+current);
		        while(true){
		        	Log.d(TAG, "current = "+System.currentTimeMillis());
		        	if((System.currentTimeMillis()-current)/1000==5){
		        		myHandler.sendMessage(new Message());
		        		return;
		        	}
		        }
			}
        }).start();
		
		toastgoButton = (Button) findViewById(R.id.toastgo);
		toastgoButton.setOnClickListener(new IntroButtonListener());
		activitygoButton = (Button) findViewById(R.id.activitygo);
		activitygoButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MainActivity.this.startActivity(new Intent(MainActivity.this,ActivityToast.class));
			}
		});
		backButton = (Button) findViewById(R.id.activityback);
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(my!=null){
					my.hide();
				}
				
			}
		});
    }
    private class IntroButtonListener implements OnClickListener {
         public void onClick(View v) {
        	if(my!=null) my.hide();
        	my = new MyToast(MainActivity.this);
     		my.show("                                                         ", -1);
        }
    }


    Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(my!=null){
//				my.hide();
			}
//			MainActivity.this.finish();
		}
    	
    };
}