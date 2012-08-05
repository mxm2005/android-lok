package com.wz.nurse.util;

import com.wz.nurse.R;
import com.wz.nurse.bean.DialogBean;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.PopupWindow;

public class DialogUtil {
	private boolean flag = false;
	private PopupWindow popupWindow;
	
	public void dialog1(Activity activity, DialogBean db) {
		AlertDialog.Builder builder = new Builder(activity);
		builder.setIcon(db.getIcon());
		builder.setMessage(db.getMessage());
		builder.setTitle(db.getTitle());
		builder.setPositiveButton(db.getPositiveButton(), new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setNegativeButton(db.getNegativeButton(), new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
	
	public void dialog2(Activity activity) {
		Dialog dialog = new AlertDialog.Builder(activity)
				.setIcon(android.R.drawable.btn_star).setTitle("喜好调查")
				.setMessage("你喜欢李连杰的电影吗？")
				.setPositiveButton("很喜欢", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).setNegativeButton("不喜欢", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).setNeutralButton("一般", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create();

		dialog.show();
	}
	
	public void dialog3(Activity activity) {
		new AlertDialog.Builder(activity).setTitle("请输入")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(new EditText(activity)).setPositiveButton("确定", null)
				.setNegativeButton("取消", null).show();
	}
	
	public void dialog4(Activity activity) {
		new AlertDialog.Builder(activity)
				.setTitle("复选框")
				.setMultiChoiceItems(new String[] { "Item1", "Item2" }, null,
						null).setPositiveButton("确定", null)
				.setNegativeButton("取消", null).show();
	}
	
	public void dialog5(Activity activity) {
		new AlertDialog.Builder(activity)
				.setTitle("单选框")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setSingleChoiceItems(new String[] { "Item1", "Item2" }, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).setNegativeButton("取消", null).show();
	}
	
	public void dialog6(Activity activity) {
		new AlertDialog.Builder(activity).setTitle("列表框")
				.setItems(new String[] { "Item1", "Item2" }, null)
				.setNegativeButton("确定", null).show();
	}
	
	public void createPopupWindowns(Activity activity) {
		LayoutInflater inflater = LayoutInflater.from(activity);
		View layout = inflater.inflate(R.layout.popup, null);
		popupWindow = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}
	
//	private void openMenu() {
//		if (!flag) {
//			popupWindow.setAnimationStyle(R.style.PopupAnimation);
//			popupWindow.showAtLocation(findViewById(R.id.btn_show), Gravity.CENTER, 0, 0);
//			popupWindow.setFocusable(true);
//			popupWindow.update();
//			flag = true;
//		} else {
//			popupWindow.dismiss();
//			popupWindow.setFocusable(false);
//			flag = false;
//		}
//	}
}
