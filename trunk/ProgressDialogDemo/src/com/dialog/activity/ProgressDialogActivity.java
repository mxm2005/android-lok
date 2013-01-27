package com.dialog.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class ProgressDialogActivity extends Activity {
	
	private final int PROGRESS_DIALOG = 1;
	
	private final int INCREASE = 0;
	
	private ProgressDialog progressDialog = null;
	
	private Handler handler = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog_layout);
        
        Button button = (Button) findViewById(R.id.button);
        View.OnClickListener listener = 
        	new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					showDialog(PROGRESS_DIALOG);
				}
			};
		button.setOnClickListener(listener);
		
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch(msg.what) {
					case INCREASE:
						progressDialog.incrementProgressBy(1);
						if(progressDialog.getProgress() >= 100) {
							progressDialog.dismiss();
						}
						break;
				}
				super.handleMessage(msg);
			}
		};
    }
    
    @Override
    public Dialog onCreateDialog(int id) {
    	switch(id) {
	    	case PROGRESS_DIALOG:
				//this表示该对话框是针对当前Activity的
				progressDialog = new ProgressDialog(this);
				//设置最大值为100
				progressDialog.setMax(100);
				//设置进度条风格STYLE_HORIZONTAL
				progressDialog.setProgressStyle(
						ProgressDialog.STYLE_HORIZONTAL);
				progressDialog.setTitle("进度条对话框");
				progressDialog.setCancelable(false);
	    		break;
    	}
    	return progressDialog;
    }
    
	@Override
    public void onPrepareDialog(int id, Dialog dialog) {
    	
    	switch(id) {
	    	case PROGRESS_DIALOG:
	    		//将进度条清0
	    		progressDialog.incrementProgressBy(-progressDialog.getProgress());
	    		new Thread() {
		    		public void run() {
		    			for(int i=0; i<=100; i++) {
		    				handler.sendEmptyMessage(INCREASE);
							if(progressDialog.getProgress() >= 100) {
								break;
							}
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			    		}
		    		}
	    		}.start();
	    		break;
    	}
    }
}