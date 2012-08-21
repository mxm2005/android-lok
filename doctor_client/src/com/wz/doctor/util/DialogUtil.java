package com.wz.doctor.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class DialogUtil {
	private Activity context;
	
	public DialogUtil(Activity context) {
		this.context = context;
	}
	
	public void dialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("确认退出吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	public void dialog2() {
		Dialog dialog = new AlertDialog.Builder(context)
				.setIcon(android.R.drawable.btn_star)
				.setTitle("喜好调查")
				.setMessage("你喜欢李连杰的电影吗？")
				.setPositiveButton("很喜欢",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Toast.makeText(context,
										"我很喜欢他的电影。", Toast.LENGTH_LONG).show();
							}
						})
				.setNegativeButton("不喜欢",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Toast.makeText(context,
										"我不喜欢他的电影。", Toast.LENGTH_LONG).show();
							}
						})
				.setNeutralButton("一般", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "谈不上喜欢不喜欢。",
								Toast.LENGTH_LONG).show();
					}
				}).create();
		dialog.show();
	}
	
	public void dialog3() {
		new AlertDialog.Builder(context).setTitle("请输入")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(new EditText(context)).setPositiveButton("确定", null)
				.setNegativeButton("取消", null).show();
	}
	
	public void dialog4() {
		new AlertDialog.Builder(context)
				.setTitle("复选框")
				.setMultiChoiceItems(new String[] { "Item1", "Item2" }, null,
						null).setPositiveButton("确定", null)
				.setNegativeButton("取消", null).show();
	}
	
	public void dialog5() {
		new AlertDialog.Builder(context)
				.setTitle("单选框")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setSingleChoiceItems(new String[] { "Item1", "Item2" }, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).setNeutralButton("确定", null)
				.setNegativeButton("取消", null).show();
	}
	
	public void dialog6() {
		new AlertDialog.Builder(context).setTitle("列表框")
				.setItems(new String[] { "Item1", "Item2" }, null)
				.setNegativeButton("确定", null).show();
	}
}
