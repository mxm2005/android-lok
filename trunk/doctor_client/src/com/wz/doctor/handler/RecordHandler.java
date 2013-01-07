package com.wz.doctor.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;

public class RecordHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_record_list;
	private LinearLayout record_sumarry;
	private ListView list_record;
	private ImageButton btn_record_add;
	private SimpleAdapter mSimpleAdapter;
	private LinearLayout lin_summary;
	private ImageButton btn_record_start_pause;
	private TextView tv_size_time;

	private MediaRecorder mRecorder = null;
	private MediaPlayer mPlayer = null;
	
	private static String mFileName = null;
	private static boolean recordFlag = false;
	
	public RecordHandler(HomeActivity mHomeActivity, LinearLayout lin_summary) {
		this.lin_summary = lin_summary;
		this.mHomeActivity = mHomeActivity;
		layoutInflater = mHomeActivity.getLayoutInflater();
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		mFileName += "/audiorecordtest.3gp";
	}
	
	/**
	 * 左边summary
	 * @return
	 */
	public LinearLayout recordSummary() {
		record_sumarry = (LinearLayout) layoutInflater.inflate(R.layout.record_sumarry, null);
		list_record = (ListView) record_sumarry.findViewById(R.id.list_record);
		mSimpleAdapter = new SimpleAdapter(mHomeActivity, 
				getData(), 
				R.layout.list_record_summary, 
				new String[]{ "tv_name", "tv_size", "tv_date" }, 
				new int[]{ R.id.tv_name, R.id.tv_size, R.id.tv_date });
		list_record.setAdapter(mSimpleAdapter);
		list_record.setSelector(R.drawable.btn_locallist_p);
		btn_record_add = (ImageButton) record_sumarry.findViewById(R.id.btn_record_add);
		btn_record_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lin_summary.removeAllViews();
				lin_summary.addView(recordingDetail());
			}
		});
		return record_sumarry;
	}
	
	private void onRecord(boolean start)
	{
		if(start)
		{
			startRecording();
		}
		else
		{
			stopRecording();
		}
	}

	private void startRecording()
	{
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try
		{
			mRecorder.prepare();
		}
		catch (IOException e)
		{
			Log.e("", "prepare() failed");
		}

		mRecorder.start();
	}

	private void stopRecording()
	{
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

	private boolean timeFlag = false; 
	
	public LinearLayout recordingDetail() {
		tab_record_list = (LinearLayout) layoutInflater.inflate(R.layout.tab_record_detail, null);
		tv_size_time = (TextView) tab_record_list.findViewById(R.id.tv_size_time);
		tv_size_time.setText("00:02");//初始化，从数据库里读取
		btn_record_start_pause = (ImageButton) tab_record_list.findViewById(R.id.btn_record_start_pause);
		btn_record_start_pause.setBackgroundResource(R.drawable.btn_start_bg);
		btn_record_start_pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("", "button on click");
				final Timer timer = new Timer();
				if(!recordFlag)
				{
					Log.i("", "recordFlag..");
					timeFlag = false;
					recordFlag = !recordFlag;
					btn_record_start_pause.setBackgroundResource(R.drawable.btn_pause_bg);
					timer.schedule(new MyTask(), 0, 1000);// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
					onRecord(recordFlag);
				}
				else
				{
					recordFlag = !recordFlag;
					btn_record_start_pause.setBackgroundResource(R.drawable.btn_start_bg);
				}
				new Thread()
				{
					public void run()
					{
						while(!timeFlag)
						{// 这个是用来停止此任务的,否则就一直循环执行此任务了
							if(!recordFlag)
							{
								timer.cancel();// 使用这个方法退出任务
								timeFlag = true;
							}
						}
					};
				}.start();
			}
		});
		return tab_record_list;
	}
	
	private int i = 1;//初始值
	
	class MyTask extends java.util.TimerTask
	{
		@Override
		public void run()
		{
			mHandler.sendEmptyMessage(i);
		}
	}
	
	Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			tv_size_time.setText((timeSystem(i++)));
			super.handleMessage(msg);
		}
	};
	
	private static String timeSystem(int time)
	{
		int minute = time / 60;
		int second = time % 60;
		StringBuilder sb = new StringBuilder();
		if(minute <= 9)
			sb.append("0").append(minute);
		else
			sb.append(minute);
		sb.append(":");
		if(second <= 9)
			sb.append("0").append(second);
		else
			sb.append(second);
		return sb.toString();
	}
	
	private List<? extends Map<String, ?>> getData() {
		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = null;
		maps = new HashMap<String, Object>();
		maps.put("tv_name", "我的备忘录1");
		maps.put("tv_size", "00:01");
		maps.put("tv_date", "2013-01-04 12:57:51");
		records.add(maps);
		return records;
	}
	
}
