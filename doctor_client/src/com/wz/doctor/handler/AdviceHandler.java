package com.wz.doctor.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.wz.doctor.AddAdviceActivity;
import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;
import com.wz.doctor.util.JSONUtil;
/**
 * 医嘱
 * @author yjh
 *
 */
public class AdviceHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_advice_list;
	private LinearLayout advice_sumarry;
	private LinearLayout lin_advice_all;
	private LinearLayout lin_advice_long;
	private LinearLayout lin_advice_temporary;
	private ImageButton cb_all;
	private ImageButton cb_long;
	private ImageButton cb_temporary;
	private LinearLayout lin_advice_current;
	private LinearLayout lin_advice_today;
	private LinearLayout lin_advice_yesterday;
	private LinearLayout lin_advice_week;
	private LinearLayout lin_advices_all;
	private PopupWindow popupWindow;
	private boolean flag = false;
	private View layout;

	public AdviceHandler(HomeActivity mHomeActivity) {
		this.mHomeActivity = mHomeActivity;
		layoutInflater = mHomeActivity.getLayoutInflater();
	}
	
	/**
	 * 左边summary
	 * @return
	 */
	public LinearLayout adviceSummary() {
		advice_sumarry = (LinearLayout) layoutInflater.inflate(R.layout.advice_sumarry, null);
		lin_advice_all = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advice_all);
		lin_advice_long = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advice_long);
		lin_advice_temporary = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advice_temporary);
		cb_all = (ImageButton) advice_sumarry.findViewById(R.id.cb_all);
		cb_long = (ImageButton) advice_sumarry.findViewById(R.id.cb_long);
		cb_temporary = (ImageButton) advice_sumarry.findViewById(R.id.cb_temporary);
		lin_advice_all.setOnClickListener(buttonListener);
		lin_advice_long.setOnClickListener(buttonListener);
		lin_advice_temporary.setOnClickListener(buttonListener);
		lin_advice_current = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advice_current);
		lin_advice_today = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advice_today);
		lin_advice_yesterday = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advice_yesterday);
		lin_advice_week = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advice_week);
		lin_advices_all = (LinearLayout) advice_sumarry.findViewById(R.id.lin_advices_all);
		lin_advice_current.setOnClickListener(buttonListener);
		lin_advice_today.setOnClickListener(buttonListener);
		lin_advice_yesterday.setOnClickListener(buttonListener);
		lin_advice_week.setOnClickListener(buttonListener);
		lin_advices_all.setOnClickListener(buttonListener);
		return advice_sumarry;
	}

	/**
	 * 加载医嘱界面
	 */
	public LinearLayout adviceList() {
		tab_advice_list = (LinearLayout) layoutInflater.inflate(
				R.layout.tab_advice_list, null);
		ListView lv_advice = (ListView) tab_advice_list
				.findViewById(R.id.lv_advice);
//		btn_implement = (Button) tab_advice_list.findViewById(R.id.btn_implement);
		SimpleAdapter sAdapter = new SimpleAdapter(mHomeActivity, getData(),
				R.layout.advice_list_item, new String[] { "NVAF11", "VAF45",
						"VAF22N", "VAF26", "BCE03A", "NBCK03" }, new int[] {
						R.id.tvType, R.id.tvTime, R.id.tvDetail, R.id.tvUsage,
						R.id.tvPeople, R.id.tvOffices });
		lv_advice.setAdapter(sAdapter);
		lv_advice.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		lv_advice.setSelector(R.drawable.btn_locallist_p);
		Button btn_add = (Button) tab_advice_list.findViewById(R.id.btn_add);
		btn_add.setOnClickListener(buttonListener);
		Button btn_del = (Button) tab_advice_list.findViewById(R.id.btn_del);
		btn_del.setOnClickListener(buttonListener);
		Button btn_merge = (Button) tab_advice_list.findViewById(R.id.btn_merge);
		btn_merge.setOnClickListener(buttonListener);
		return tab_advice_list;
	}

	private List<? extends Map<String, ?>> getData() {
		JSONUtil ju = new JSONUtil();// 解析json类
		List<Map<String, Object>> advices = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = null;
		try {
			List<Map<String, Object>> lists = ju.getData(mHomeActivity,
					"doctors_advice.json");
			for (Map<String, Object> map : lists) {
				maps = new HashMap<String, Object>();
				maps.put("NVAF11", (String) map.get("NVAF11"));
				maps.put("VAF45", (String) map.get("VAF45"));
				maps.put("VAF22N", (String) map.get("VAF22N"));
				maps.put("VAF26", (String) map.get("VAF26"));
				maps.put("BCE03A", (String) map.get("BCE03A"));
				maps.put("NBCK03", (String) map.get("NBCK03"));
				advices.add(maps);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return advices;
	}
	private OnClickListener buttonListener = new OnClickListener() {

		@SuppressWarnings("static-access")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = null;
			switch (v.getId()) {
			case R.id.btn_add:
				intent = new Intent(mHomeActivity,
						AddAdviceActivity.class);
				mHomeActivity.startActivity(intent);
				break;
			case R.id.btn_del:
				dialog();
				break;
			case R.id.btn_merge:
				LayoutInflater inflater = LayoutInflater.from(mHomeActivity);
		        layout = inflater.inflate(R.layout.toast, null);
				popupWindow = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
		                LayoutParams.WRAP_CONTENT);
				Button btn_implement = (Button) layout.findViewById(R.id.btn_implement);
				Button btn_cancel = (Button) layout.findViewById(R.id.btn_cancel);
				btn_implement.setOnClickListener(buttonListener);
				btn_cancel.setOnClickListener(buttonListener);
				openMenu();
				break;
			case R.id.lin_advice_all://选择全部
				lin_advice_all.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_long.setBackgroundDrawable(null);
				lin_advice_temporary.setBackgroundDrawable(null);
				cb_all.setVisibility(View.VISIBLE);
				cb_long.setVisibility(View.INVISIBLE);
				cb_temporary.setVisibility(View.INVISIBLE);
				break;
			case R.id.lin_advice_long://选择长期医嘱
				lin_advice_long.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_all.setBackgroundDrawable(null);
				lin_advice_temporary.setBackgroundDrawable(null);
				cb_all.setVisibility(View.INVISIBLE);
				cb_long.setVisibility(View.VISIBLE);
				cb_temporary.setVisibility(View.INVISIBLE);
				break;
			case R.id.lin_advice_temporary://选择临时医嘱
				lin_advice_temporary.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_long.setBackgroundDrawable(null);
				lin_advice_all.setBackgroundDrawable(null);
				cb_all.setVisibility(View.INVISIBLE);
				cb_long.setVisibility(View.INVISIBLE);
				cb_temporary.setVisibility(View.VISIBLE);
				break;
			case R.id.lin_advice_current://选择当前医嘱
				lin_advice_current.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_today.setBackgroundDrawable(null);
				lin_advice_yesterday.setBackgroundDrawable(null);
				lin_advice_week.setBackgroundDrawable(null);
				lin_advices_all.setBackgroundDrawable(null);
				break;
			case R.id.lin_advice_today://选择今日医嘱
				lin_advice_today.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_current.setBackgroundDrawable(null);
				lin_advice_yesterday.setBackgroundDrawable(null);
				lin_advice_week.setBackgroundDrawable(null);
				lin_advices_all.setBackgroundDrawable(null);
				break;
			case R.id.lin_advice_yesterday://选择昨日医嘱
				lin_advice_yesterday.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_today.setBackgroundDrawable(null);
				lin_advice_current.setBackgroundDrawable(null);
				lin_advice_week.setBackgroundDrawable(null);
				lin_advices_all.setBackgroundDrawable(null);
				break;
			case R.id.lin_advice_week://选择最近一周的医嘱
				lin_advice_week.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_today.setBackgroundDrawable(null);
				lin_advice_yesterday.setBackgroundDrawable(null);
				lin_advice_current.setBackgroundDrawable(null);
				lin_advices_all.setBackgroundDrawable(null);
				break;
			case R.id.lin_advices_all://全部医嘱
				lin_advices_all.setBackgroundResource(R.drawable.word_list_selector_selected);
				lin_advice_today.setBackgroundDrawable(null);
				lin_advice_yesterday.setBackgroundDrawable(null);
				lin_advice_week.setBackgroundDrawable(null);
				lin_advice_current.setBackgroundDrawable(null);
				break;
			case R.id.btn_implement://跳转到执行医嘱界面
//				intent = new Intent(mHomeActivity, null);
//				mHomeActivity.startActivity(intent);
				break;
			case R.id.btn_cancel:
				openMenu();
				NotificationManager manager = (NotificationManager) mHomeActivity.getSystemService(Context.NOTIFICATION_SERVICE);  
                Notification notification = new Notification(R.drawable.icon, "新医嘱", System.currentTimeMillis());
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                notification.defaults = notification.DEFAULT_SOUND;
                notification.setLatestEventInfo(mHomeActivity, "执行新医嘱", "点击执行", PendingIntent.getActivity(mHomeActivity, 0, intent, 0));  
                manager.notify(0, notification);
				break;
			}
		}
	};
	
	/**
	 * 新的医嘱提醒
	 */
	public void openMenu() {
        if (!flag) {
            popupWindow.setAnimationStyle(R.style.PopupAnimation);
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
            popupWindow.setFocusable(true);
            popupWindow.update();
            flag = true;
        } else {
            popupWindow.dismiss();
            popupWindow.setFocusable(true);
            flag = false;
        }
    }
	
	/**
	 * 退出的提醒
	 */
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(mHomeActivity);
		builder.setMessage("确认要删除吗？");
		builder.setIcon(android.R.drawable.ic_dialog_info);
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
}
