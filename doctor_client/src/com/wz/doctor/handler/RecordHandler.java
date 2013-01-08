package com.wz.doctor.handler;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wz.doctor.HomeActivity;
import com.wz.doctor.R;
import com.wz.doctor.bean.Record;
import com.wz.doctor.db.RecordService;

public class RecordHandler {
	private HomeActivity mHomeActivity;
	private LayoutInflater layoutInflater;
	private LinearLayout tab_record_list;
	private LinearLayout record_sumarry;
	private ListView list_record;
	private ImageButton btn_record_add;
	private SimpleAdapter mSimpleAdapter;
	private LinearLayout lin_lv_tab;
	private LinearLayout lin_summary;
	private ImageButton btn_record_start_pause;
	private TextView tv_size_time;

	private MediaRecorder mRecorder = null;
	private MediaPlayer mPlayer = null;
	
	private static String mFileName = null;
	private boolean recordFlag = false;
	
	private Button btn_save_record;
	private Button btn_cancel_record;
	private EditText et_theme;
	private RecordService rService;
	private String fileName;
	
	private LinearLayout tab_play_list;
	
	private EditText tv_title_record;
	private TextView tv_size_record;
	private TextView tv_save_date;
	private Button btn_edit_record;
	private Button btn_delete_record;
	private ImageButton ib_memo_play;
	private SeekBar seek_volume;
	private SeekBar seek_progress;
	private boolean editFlag = false;
	private boolean playFlag = false;
	private boolean timeFlag = false;
	private boolean pauseFlag = false;
	private AudioManager mAudioManager;
	
	private TextView tv_seekTo;
	private int duration;
	private String playFile;
	private int item_id;
	

	private int record = 1;//初始值
	private int play = 1;

	
	public RecordHandler(HomeActivity mHomeActivity, LinearLayout lin_lv_tab, LinearLayout lin_summary) {
		this.lin_lv_tab = lin_lv_tab;
		this.lin_summary = lin_summary;
		this.mHomeActivity = mHomeActivity;
		layoutInflater = mHomeActivity.getLayoutInflater();
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
		rService = new RecordService(mHomeActivity);
		mAudioManager = (AudioManager) mHomeActivity.getSystemService(Service.AUDIO_SERVICE);
	}
	
	public LinearLayout recordSummary() {
		record_sumarry = (LinearLayout) layoutInflater.inflate(R.layout.record_sumarry, null);
		tab_play_list = (LinearLayout) layoutInflater.inflate(R.layout.tab_play_detail, null);
		btn_record_add = (ImageButton) record_sumarry.findViewById(R.id.btn_record_add);
		btn_record_add.setOnClickListener(buttonListener);
		list_record = (ListView) record_sumarry.findViewById(R.id.list_record);
		mSimpleAdapter = new SimpleAdapter(mHomeActivity, 
				getData(), 
				R.layout.list_record_summary, 
				new String[]{"tv_id", "tv_name", "tv_size", "tv_date" }, 
				new int[]{ R.id.tv_id, R.id.tv_name, R.id.tv_size, R.id.tv_date });
		list_record.setAdapter(mSimpleAdapter);
		list_record.setSelector(R.drawable.btn_locallist_p);
		list_record.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int id, long position) {
				String tv_name = ((TextView)view.findViewById(R.id.tv_name)).getText().toString().trim();
				playFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + tv_name + ".3gp";
				String tv_id = ((TextView)view.findViewById(R.id.tv_id)).getText().toString().trim();
				item_id = Integer.parseInt(tv_id);
				tv_title_record = (EditText) tab_play_list.findViewById(R.id.tv_title_record);
				tv_size_record = (TextView) tab_play_list.findViewById(R.id.tv_size_record);
				tv_save_date = (TextView) tab_play_list.findViewById(R.id.tv_save_date);
				String tv_size = ((TextView)view.findViewById(R.id.tv_size)).getText().toString().trim();
				String tv_date = ((TextView)view.findViewById(R.id.tv_date)).getText().toString().trim();
				tv_title_record.setText(tv_name);
				tv_size_record.setText(tv_size);
				tv_save_date.setText(tv_date);
				
				btn_edit_record = (Button) tab_play_list.findViewById(R.id.btn_edit_record);
				btn_edit_record.setOnClickListener(buttonListener);
				
				btn_delete_record = (Button) tab_play_list.findViewById(R.id.btn_delete_record);
				btn_delete_record.setOnClickListener(buttonListener);
				
				ib_memo_play = (ImageButton) tab_play_list.findViewById(R.id.ib_memo_play);
				ib_memo_play.setOnClickListener(buttonListener);
				
				seek_volume = (SeekBar) tab_play_list.findViewById(R.id.seek_volume);
				int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				int currentVolume = mAudioManager.getStreamVolume (AudioManager.STREAM_MUSIC);
				seek_volume.setMax(maxVolume);
				seek_volume.setProgress(currentVolume);
				seek_volume.setOnSeekBarChangeListener(seekBarListener);
				
				seek_progress = (SeekBar) tab_play_list.findViewById(R.id.seek_progress);
				duration = string2Time(tv_size);
				System.out.println("duration: " + duration);
				seek_progress.setMax(duration);
				
				seek_progress.setOnSeekBarChangeListener(seekBarListener);
				
				tv_seekTo = (TextView) tab_play_list.findViewById(R.id.tv_seekTo);

				lin_summary.removeAllViews();
				lin_summary.addView(tab_play_list);
			}
		});
		return record_sumarry;
	}
	
	public LinearLayout recordingDetail() {
		tab_record_list = (LinearLayout) layoutInflater.inflate(R.layout.tab_record_detail, null);
		tv_size_time = (TextView) tab_record_list.findViewById(R.id.tv_size_time);
		btn_record_start_pause = (ImageButton) tab_record_list.findViewById(R.id.btn_record_start_pause);
		et_theme = (EditText) tab_record_list.findViewById(R.id.et_theme);
		btn_record_start_pause.setBackgroundResource(R.drawable.btn_start_bg);
		btn_record_start_pause.setOnClickListener(buttonListener);
		btn_save_record = (Button) tab_record_list.findViewById(R.id.btn_save_record);
		btn_save_record.setOnClickListener(buttonListener);
		btn_cancel_record = (Button) tab_record_list.findViewById(R.id.btn_cancel_record);
		btn_cancel_record.setOnClickListener(buttonListener);
		return tab_record_list;
	}
	
	private OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener()
	{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		{
			switch (seekBar.getId())
			{
			case R.id.seek_volume:
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);
				break;

			case R.id.seek_progress:
				
				break;
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar)
		{
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar)
		{
			
		}
		
	};
	
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
		mFileName += (fileName + ".3gp");
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
	
	private void onPlay(boolean start) {
		if (start) {
			startPlaying();
		} else {
			pausePlaying();
		}
	}

	private void startPlaying() {
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(playFile);
			mPlayer.prepare();
			mPlayer.start();
			mPlayer.setOnCompletionListener(new OnCompletionListener()
			{
				@Override
				public void onCompletion(MediaPlayer mp)
				{
					Log.i("", "the data is finish..");
					stopPlaying();
					playTimer.cancel();
					timeFlag = true;
					playFlag = false;
					ib_memo_play.setBackgroundResource(R.drawable.memo_play);
					Toast.makeText(mHomeActivity, "播放完毕", Toast.LENGTH_SHORT).show();
					tv_seekTo.setText("00:00");
					seek_progress.setProgress(0);
					stopPlaying();
				}
			});
		} catch (IOException e) {
			Log.e("", "prepare() failed");
		}
		new Thread()
		{
			public void run()
			{
				while(!timeFlag)
				{// 这个是用来停止此任务的,否则就一直循环执行此任务了
					Log.i("", "I am a play thread");
					if(!playFlag)
					{
						playTimer.cancel();// 使用这个方法退出任务
						timeFlag = true;
					}
					try
					{
						sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	
	private void pausePlaying()
	{
		mPlayer.pause();
		mPlayer.seekTo(play);
	}

	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
	}
	
	final Timer playTimer = new Timer();
	final Timer recordTimer = new Timer();
	
	private OnClickListener buttonListener = new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.btn_record_add:
				lin_summary.removeAllViews();
				lin_summary.addView(recordingDetail());
				break;

			case R.id.btn_record_start_pause:
				Log.i("", "button on click");
				String title = et_theme.getText().toString().trim();
				fileName = title;
				if (!"".equals(title) && title != null) {
					onRecord(!recordFlag);
					if(!recordFlag)
					{
						record = 1;//暂停后设为初始值
						Log.i("", "recordFlag..");
						timeFlag = false;
						recordFlag = true;
						btn_record_start_pause.setBackgroundResource(R.drawable.btn_pause_bg);
						recordTimer.schedule(new RecordTask(), 0, 1000);// 在0秒后执行此任务,每次间隔1秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
					}
					else
					{
						timeFlag = true;
						recordFlag = false;
						btn_record_start_pause.setBackgroundResource(R.drawable.btn_start_bg);
						recordTimer.cancel();
						Toast.makeText(mHomeActivity, "请存储备忘录，否则可能丢失", Toast.LENGTH_SHORT).show();
					}
					new Thread()
					{
						public void run()
						{
							while(!timeFlag)
							{// 这个是用来停止此任务的,否则就一直循环执行此任务了
								Log.i("", "I am a record thread");
								if(!recordFlag)
								{
									recordTimer.cancel();// 使用这个方法退出任务
									timeFlag = true;
								}
								try
								{
									sleep(1000);
								}
								catch (InterruptedException e)
								{
									e.printStackTrace();
								}
							}
						};
					}.start();
				} else {
					Toast.makeText(mHomeActivity, "请输入备忘主题", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.btn_save_record:
				String fileName = et_theme.getText().toString().trim();
				if(record > 1) {
					//存储音频文件
					timeFlag = true;
					Date now = new Date();
					DateFormat df = DateFormat.getDateTimeInstance();
					String date = df.format(now);
					rService.saveRecord(new Record(fileName, record, date));
					Toast.makeText(mHomeActivity, "备忘录保存成功", Toast.LENGTH_SHORT).show();
					lin_lv_tab.removeAllViews();
					lin_lv_tab.addView(recordSummary());
					lin_summary.removeAllViews();
					recordTimer.cancel();
				} else {
					Toast.makeText(mHomeActivity, "您还没开始录音，请录音后再存储", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.btn_cancel_record:
				lin_summary.removeAllViews();
				break;
				
			case R.id.btn_edit_record:
				if(playFlag)
				{
					Toast.makeText(mHomeActivity, "播放备忘录状态下不可编辑", Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(!editFlag)
					{
						btn_edit_record.setText("完成");
						tv_title_record.setEnabled(true);
						tv_title_record.setFocusable(true);
						tv_title_record.clearFocus();
						tv_title_record.setSelection(tv_title_record.getText().toString().length());
						editFlag = true;
					}
					else
					{
						//更新数据库
						//更新左边listview
						btn_edit_record.setText("编辑");
						editFlag = false;
					}
				}
				break;
			case R.id.btn_delete_record:
				//更新数据库
				//更新左边listView 
				//更新右边
				//删除源文件
				new AlertDialog.Builder(mHomeActivity).setTitle("提示")
				.setMessage("确定删除该备忘记录吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						rService.deleteRecord(item_id);
						File fileDel = new File(new File(playFile).getAbsolutePath());
						if(fileDel.exists())
							fileDel.delete();
						lin_summary.removeAllViews();
						lin_lv_tab.removeAllViews();
						lin_lv_tab.addView(recordSummary());
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.cancel();
					}
				}).show();
				break;
			case R.id.ib_memo_play:
				//播放备忘录
				Log.i("", "ib_memo...............");
//				pauseFlag = false;
//				if(!pauseFlag){
//					ib_memo_play.setBackgroundResource(R.drawable.btn_pause_bg);
//				}
				onPlay(!playFlag);
				if(!playFlag)
				{
					playFlag = true;
					timeFlag = false;
					ib_memo_play.setBackgroundResource(R.drawable.btn_pause_bg);
					playTimer.schedule(new PlayTask(), 0, 1000);// 在0秒后执行此任务,每次间隔1秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
				}
				else
				{
//					pauseFlag = true;
					playFlag = false;
					ib_memo_play.setBackgroundResource(R.drawable.memo_play);
				}
				break;
			}
		}
	};
	
	class RecordTask extends TimerTask
	{
		@Override
		public void run()
		{
			mHandler1.sendEmptyMessage(record);
		}
	}
	
	class PlayTask extends TimerTask
	{

		@Override
		public void run()
		{
			mHandler2.sendEmptyMessage(play);
		}
		
	}
	
	Handler mHandler1 = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			tv_size_time.setText(timeSystem(record++));
			super.handleMessage(msg);
		}
	};
	
	Handler mHandler2 = new Handler()
	{
		public void handleMessage(Message msg)
		{
			Log.i("", "play: " + play);
			seek_progress.setProgress(play);
			if(!pauseFlag)
				tv_seekTo.setText(timeSystem(play++));
			System.out.println("play++++++++++++++++++++++++++++++++++++++++++++: " + play);
			super.handleMessage(msg);
		};
	};
	
	private String timeSystem(int time)
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
	
	private int string2Time(String str) {
		int time = 0;
		if (str.indexOf(":") > -1) {
			String[] strs = str.split(":");
			time = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
		}
		return time;
	}
	
	private List<? extends Map<String, ?>> getData() {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		List<Record> records = rService.findAllRecord();
		Map<String, Object> maps = null;
		for (Record r : records) {
			maps = new HashMap<String, Object>();
			maps.put("tv_id", String.valueOf(r.getId()));
			maps.put("tv_name", r.getName());
			maps.put("tv_size", timeSystem(r.getLenght()));
			maps.put("tv_date", r.getDate());
			lists.add(maps);
		}
		return lists;
	}
	
}
