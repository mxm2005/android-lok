package com.genius.demo.clock;

import java.util.Calendar;

import android.os.SystemClock;

public class ClockTime {
	
	public int mHour = 0;
	public int mMinute = 0;
	public int mSecond = 0;
	
	public int mHourDegree = 0;							
	public int mMinuteDegree = 0;
	public int mSecondDegree = 0;
	
	
	public int mPreDegree = 0;		// 上一次分针的偏移角度
	
	private Calendar mCalendar;

	
	public void refreshTime(){						// 根据系统时间刷新值
		long time = System.currentTimeMillis(); 		
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);     
        
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mSecond = mCalendar.get(Calendar.SECOND);
        
        calcDegreeByTime();
        
        mPreDegree = mMinuteDegree;
       
	}
	
	private void calcDegreeByTime(){				// 根据时间计算指针偏移弧度
		mSecondDegree = mSecond * 6;
		
		mMinuteDegree = (int) ((mMinute + (float)mSecond / 60) * 6);
		
		mHourDegree = (mHour % 12) * 30 + mMinuteDegree / 12;
	}
	
	
	
	public void calcTime(boolean bFlag){			// 根据当前的分针角度刷新值(flag标示是否校正指针角度)
		if (mMinuteDegree >= 360){
			mMinuteDegree %= 360;
		}
		
		if (mMinuteDegree < 0){
			mMinuteDegree += 360;
		}
		
		mMinute = (int) ((mMinuteDegree / 360.0) * 60);
		mSecond = 0;
		
		if (IsDeasil()){
			if (mMinuteDegree < mPreDegree){		// 分针顺时针经过12点钟方向
				mHour += 1;
				mHour %= 24;
			}
		}else{
			if (mMinuteDegree > mPreDegree){		// 分针逆时针经过12点钟方向
				mHour -= 1;
				if (mHour < 0){
					mHour += 24;
				}
			}
		}
		
		mHourDegree = (mHour % 12) * 30 + mMinuteDegree / 12;
		
		
		if (bFlag){				
			calcDegreeByTime();
		}
		
		mPreDegree = mMinuteDegree;
		
	}
	
	private boolean IsDeasil(){					// 分针移动时是否是顺时针方向
		if (mMinuteDegree >= mPreDegree){
			if (mMinuteDegree - mPreDegree < 180){
				return true;
			}
			return false;
		}else{
			if (mPreDegree - mMinuteDegree > 180){
				return true;
			}
			
			return false;
		}
	}
	
	public void saveTimeToSystem()				// 更新当前时间到系统时间
	{
		long time = System.currentTimeMillis(); 		
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);     
        
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DATE);
        
        mCalendar.set(year, month, day, mHour, mMinute, 0);
		
		long curTime = mCalendar.getTimeInMillis();
		SystemClock.setCurrentTimeMillis(curTime);
	}
	
}
