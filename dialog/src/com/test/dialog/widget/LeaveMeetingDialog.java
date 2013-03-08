package com.test.dialog.widget;

import com.test.dialog.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LeaveMeetingDialog extends Dialog implements OnClickListener
{

	private Button dialog_button_confirm;
	private Button dialog_button_cancel;

	private LeaveMeetingDialogListener listener;

	public interface LeaveMeetingDialogListener
	{
		public void onClick(View v);
	}

	public LeaveMeetingDialog(Context context, int theme, LeaveMeetingDialogListener listener)
	{
		super(context, theme);
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog);
		dialog_button_confirm = (Button) findViewById(R.id.dialog_button_confirm);
		dialog_button_cancel = (Button) findViewById(R.id.dialog_button_cancel);

		dialog_button_confirm.setOnClickListener(this);
		dialog_button_cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		listener.onClick(v);
	}
}
