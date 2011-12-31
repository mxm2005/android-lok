package com.genius.demo.clock;

import com.genius.demo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;

public class CustomClock extends View{
	private final String TAG = "CustomClock";
	
	private final static int REFRESH_TIME_EVENT = 0x100;
	
	private Drawable mDialPicture;								// 时钟背景图
	
	private Bitmap   mCirclePicture;							// 中心点图
	private Bitmap   mHourPictrue;								// 时针图
	private Bitmap   mMinutePictrue;							// 分针图
	private Bitmap   mSecondPictrue;							// 秒针图
	
	private int mClockCenterX = 0, mClockCenterY = 0;			// 时钟中心点位置
	
	private int mViewWidth = 0, mViewHeight = 0;				// 视图大小
	
	private ClockTime		mClockTime;							// 时钟时间类
	
	private ClockPaint		mClockPaint;						// 时钟画笔类
	
	private ClockTimer      mClockTimer;						// 时钟定时器类
	
	private Handler			mHandler;				
	
	private _ClockState		mClockState;						// 当前时钟状态
	
	static public enum _ClockState
	{
		eQ_CLOCK_RUN,											// 走动状态
		eQ_CLOCK_SETTING,										// 设置状态
		eQ_CLOCK_STOP											// 停止状态
	}
	
	public CustomClock(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		initParam();
	}
	

	public CustomClock(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		initParam();
		

		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomClock);      
	        
        int mBackgroundColor = array.getColor(R.styleable.CustomClock_BackGround_Color,0xff111111);
        mClockPaint.setDialColor(mBackgroundColor);
        
        mClockCenterX = array.getInt(R.styleable.CustomClock_CenterX, 0);  
        mClockCenterY = array.getInt(R.styleable.CustomClock_CenterY, 0);
        
        int circleColor = array.getInt(R.styleable.CustomClock_Circle_Color, 0xffffffff);
        mClockPaint.setCircleColor(circleColor);
        
        
        
        Drawable cp = array.getDrawable(R.styleable.CustomClock_Circle_Pic);
        if (cp != null)
        {
        	BitmapDrawable bd = (BitmapDrawable) cp;
        	mCirclePicture = bd.getBitmap();
        }
        
        
        Drawable hp = array.getDrawable(R.styleable.CustomClock_Hour_Pic);
        if (hp != null)
        {
        	BitmapDrawable bd = (BitmapDrawable) hp;
        	mHourPictrue = bd.getBitmap();
        }
        
        Drawable mp = array.getDrawable(R.styleable.CustomClock_Minute_Pic);
        if (mp != null)
        {
        	BitmapDrawable bd = (BitmapDrawable) mp;
        	mMinutePictrue = bd.getBitmap();
        }
        
        Drawable sp = array.getDrawable(R.styleable.CustomClock_Second_Pic);
        if (sp != null)
        {
        	BitmapDrawable bd = (BitmapDrawable) sp;
        	mSecondPictrue = bd.getBitmap();
        }
        
        
        
        array.recycle(); 
	}
	
	
	
	private void initParam()
	{
		
		mHandler = new Handler()
		{

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == REFRESH_TIME_EVENT)
				{
					mClockTime.refreshTime();
					invalidate();
				}
			}
			
		};
		
		
		mClockTime = new ClockTime();
		mClockTime.refreshTime();	
		
		mClockPaint = new ClockPaint();
		
		mClockTimer = new ClockTimer(mHandler, REFRESH_TIME_EVENT);
		
		mClockState = _ClockState.eQ_CLOCK_STOP;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		
		mDialPicture = getBackground();
		if (mDialPicture != null)
		{
			width = mDialPicture.getMinimumWidth();
			height = mDialPicture.getMinimumHeight();
		}
		
		mViewWidth = resolveSize(width, widthMeasureSpec);
		mViewHeight = resolveSize(width, heightMeasureSpec);
		
		setMeasuredDimension(mViewWidth, mViewHeight);
		
		mClockCenterX = mViewWidth/2;
		mClockCenterY = mViewHeight/2;
		
		
		
		int pointLen = Math.min(mViewWidth / 2, mViewHeight / 2); 
		
		mClockPaint.setHourLength((int)(0.4 * pointLen));
		mClockPaint.setMinuteLength((int)(0.6 * pointLen));
		mClockPaint.setSecondLength((int)(0.8 * pointLen));
		mClockPaint.setRadius((int) (0.1 * pointLen));
		
		mClockPaint.setDialRect(new RectF(0, 0, mViewWidth, mViewHeight));
		mClockPaint.setDegreeLength((int) (0.2 * pointLen));
	}
	
	
	
	
	
	
	
	
	

	
	private void drawHour(Canvas canvas){
		canvas.save();
		
		canvas.translate(mClockCenterX, mClockCenterY);		
		
		canvas.rotate(mClockTime.mHourDegree);
		
		if (mHourPictrue != null)
		{
			canvas.drawBitmap(mHourPictrue, -mHourPictrue.getWidth()/2,
					(float) ((mClockPaint.mPointRate/2.0 - 1) * mHourPictrue.getHeight()), mClockPaint.picPaint);
		}else{
			canvas.drawLine(0, mClockPaint.getHourOffsetLen(), 0, 
					(-1) * mClockPaint.getHourLength(), mClockPaint.mHourPaint);	
		}
		

		
		canvas.restore();
	}
	
	private void drawMinute(Canvas canvas){	
		canvas.save();
		
		canvas.translate(mClockCenterX, mClockCenterY);
		
		
		canvas.rotate(mClockTime.mMinuteDegree);
		
		if (mMinutePictrue != null)
		{
			canvas.drawBitmap(mMinutePictrue, -mMinutePictrue.getWidth()/2, 
					(float) ((mClockPaint.mPointRate/2.0 - 1) * mMinutePictrue.getHeight()),  mClockPaint.picPaint);
		}else{
			canvas.drawLine(0, mClockPaint.getMinuteOffsetLen(), 0, 
					(-1) * mClockPaint.getMinuteLength(), mClockPaint.mMinutePaint);
		}
		
		
		canvas.restore();
	}
	
	
	private void drawSecond(Canvas canvas)
	{
		canvas.save();
		
		canvas.translate(mClockCenterX, mClockCenterY);
		
		
		canvas.rotate(mClockTime.mSecondDegree);
		
		if (mSecondPictrue != null)
		{
			canvas.drawBitmap(mSecondPictrue, -mSecondPictrue.getWidth()/2,
					(float) ((mClockPaint.mPointRate/2.0 - 1) * mSecondPictrue.getHeight()),  mClockPaint.picPaint);
		}else{
			canvas.drawLine(0, mClockPaint.getSecondOffsetLen(), 0, 
					(-1) * mClockPaint.getSecondLength(), mClockPaint.mSecondPaint);
		}
	
		canvas.restore();
	}
	
	private void drawCircle(Canvas canvas)
	{
		canvas.save();
		
		canvas.translate(mClockCenterX, mClockCenterY);
		
		if (mCirclePicture != null)
		{
			canvas.drawBitmap(mCirclePicture, -mCirclePicture.getWidth()/2, -mCirclePicture.getWidth()/2, null);
		}else{			
			canvas.drawCircle(0, 0, mClockPaint.getRadius(), mClockPaint.mCirclePaint);
		}
	
		canvas.restore();
	}
	
	private void drawBackground(Canvas canvas)
	{
		if (mDialPicture == null)
		{
			canvas.drawArc(mClockPaint.mDialRectF, 0, 360, true, mClockPaint.mDialPaint);
			
			canvas.save();
			
			canvas.translate(mClockCenterX, mClockCenterY);
			
			for(int i = 0; i < 4; i++)
			{
				canvas.drawLine(0, -mViewHeight/2, 0, -mViewHeight/2 + mClockPaint.getDegreeLength(), mClockPaint.mDegreePaint);
				canvas.rotate(90);
			}
			
			canvas.restore();
		}
		
	}
	
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		

		drawBackground(canvas);
		
		drawHour(canvas);
		
		drawMinute(canvas);
		
		if (mClockState != _ClockState.eQ_CLOCK_SETTING)
		{
			drawSecond(canvas);	
		}
		
		drawCircle(canvas);
		
		
	}
	
	public void switchClockState(_ClockState state)			// 切换时钟状态
	{
		
		if (state == mClockState)
		{
			return ;
		}
		
		mClockState = state;
		
		switch(state)
		{
			case eQ_CLOCK_RUN:
				runClock();
				break;
			case eQ_CLOCK_SETTING:
				stopClock();
				break;
			case eQ_CLOCK_STOP:
				stopClock();
				break;
		}
		
		invalidate();	

	}
	
	public void saveTimeToSystem()							// 保存表盘时间到系统时间
	{
		mClockTime.saveTimeToSystem();
		invalidate();
	}
	
	public void reviseTime()								// 恢复当前时间到系统时间
	{
		mClockTime.refreshTime();
		invalidate();
	}
	
	private void runClock()
	{
		mClockTimer.startTimer();
	}
	
	private void stopClock()
	{
		mClockTimer.stopTimer();
	}

	/*
	 * 将视图坐标系转化为时间参考坐标系并计算指针偏移角度，flag标示是否校正指针角度
	 */
	private void calcDegree(int x, int y, boolean flag){		
		int rx = x - mClockCenterX;
		int ry = - (y - mClockCenterY);
		
		Point point = new Point(rx, ry);
		
		mClockTime.mMinuteDegree = MyDegreeAdapter.GetRadianByPos(point);
		mClockTime.calcTime(flag);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
	//	Log.i(TAG, "onTouch action = " + event.getAction());
		
		if (mClockState != _ClockState.eQ_CLOCK_SETTING)
		{
			return true;
		}
		
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
			
				calcDegree((int)event.getX(), (int)event.getY(), false);
				postInvalidate();
				
				break;
			case MotionEvent.ACTION_MOVE:
				
				calcDegree((int)event.getX(), (int)event.getY(), false);
				postInvalidate();
				
				break;
			case MotionEvent.ACTION_UP:
				
				calcDegree((int)event.getX(), (int)event.getY(), true);
				postInvalidate();
				
				break;
		}
		
		
	//	Log.i(TAG, "onTouch x = " + event.getX() + ", y = " + event.getY());
	
		
		
		
		return true;
	}
	
	
	
	
	
	
	

}
