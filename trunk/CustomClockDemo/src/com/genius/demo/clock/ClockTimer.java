package com.genius.demo.clock;

import java.util.Timer;
import java.util.TimerTask;


import android.os.Handler;
import android.os.Message;

public class ClockTimer {
	

	private int 	mEventID;
	
	private Handler mHandler;
	
	private Timer   mTimer;
	
	private ClockTimerTask	mTimerTask;		// 定时器任务
			 
	private int     mTimerInterval;		// 定时器触发间隔时间(ms)
	
	private boolean mBStartTimer;		// 定时器是否已开启
	
	public ClockTimer(Handler handler, int eventID)
	{
		initParam(handler, eventID);
	}
	
	private void initParam(Handler handler,  int eventID)
	{
		mHandler = handler;
		
		mEventID = eventID;
		
		mTimerInterval = 1000;
		
		mBStartTimer = false;
		
		mTimerTask = null;
		
		mTimer = new Timer();
	}
	
	public void startTimer()
	{
		if (mHandler == null || mBStartTimer == true)
		{
			return ;
		}
		
		mBStartTimer = true;
		mTimerTask = new ClockTimerTask();
		mTimer.schedule(mTimerTask, mTimerInterval, mTimerInterval);
		
	}
	
	public void stopTimer()
	{
		if (mBStartTimer == false)
		{
			return ;
		}
		
		mBStartTimer = false;
		if (mTimerTask != null)
		{
			mTimerTask.cancel();
			mTimerTask = null;
		}
	}
	
	
	class ClockTimerTask extends TimerTask
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (mHandler != null)
			{
				Message msgMessage = mHandler.obtainMessage(mEventID);
				msgMessage.sendToTarget();
			}
		}
		
	}
	
}
