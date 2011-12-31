package com.android.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDialog extends Dialog {

	public CustomDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	 protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 
		 setContentView(R.layout.custom_dialog);
		 setTitle("Custom Dialog");

		 TextView text = (TextView)findViewById(R.id.text);
		 text.setText("Hello, this is a custom dialog!");
		 ImageView image = (ImageView)findViewById(R.id.image);
		 image.setImageResource(R.drawable.sepurple);
		 
		 Button buttonYes = (Button) findViewById(R.id.button_yes);
		 buttonYes.setHeight(5);
		 buttonYes.setOnClickListener(new Button.OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					dismiss();
					
				}
	        });
		 Button buttonNo = (Button) findViewById(R.id.button_no);
		 buttonNo.setSingleLine(true);
		 buttonNo.setOnClickListener(new Button.OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					dismiss();
					
				}
	        });
	 }
	 
	 //called when this dialog is dismissed
	 protected void onStop() {
		 Log.d("TAG","+++++++++++++++++++++++++++");
	 }
	 

}
