package com.test.dialog;

import com.test.dialog.widget.LeaveMeetingDialog;
import com.test.dialog.widget.LeaveMeetingDialog.LeaveMeetingDialogListener;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Activity;

public class MainActivity extends Activity implements LeaveMeetingDialogListener
{
	private LeaveMeetingDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dialog = new LeaveMeetingDialog(MainActivity.this, R.style.MyDialog, this);
		dialog.show();
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
		case R.id.dialog_button_confirm:
			Log.i("", "dialog confirm");
			break;

		case R.id.dialog_button_cancel:
			Log.i("", "dialog dismiss...");
			dialog.dismiss();
			break;
		default:
			break;
		}
	}

}
