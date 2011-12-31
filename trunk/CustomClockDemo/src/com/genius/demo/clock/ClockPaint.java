package com.genius.demo.clock;

import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class ClockPaint {

	
	public Paint mHourPaint;
	public int   mHourPaintWidth;
	public int   mHourPaintColor;
	public int   mHourLength;
	
	public Paint mMinutePaint;
	public int   mMinutePaintWidth;
	public int   mMinutePaintColor;
	public int   mMinuteLength;
	
	public Paint mSecondPaint;
	public int   mSecondPaintWidth;
	public int   mSecondPaintColor;
	public int   mSecondLength;
	
	public Paint mCirclePaint;
	public int   mCircleColor;
	public int   mRadius;
	
	public Paint mDialPaint;
	public int   mDialColor;
	public RectF mDialRectF;
	
	public Paint mDegreePaint;
	public int 	 mDegreeColor;
	public int   mDegreeLength;
	public int   mDegreeWidth;

	public float mPointRate;
	
	public Paint picPaint;
	
	public ClockPaint()
	{
		initParam();
	}

	
	public void initParam()
	{
		mHourPaint = new Paint();
		mHourPaint.setAntiAlias(true);
		mHourPaintWidth = 8;
		mHourPaint.setStrokeWidth(mHourPaintWidth);
		mHourPaintColor = 0xffffff00;
		mHourPaint.setColor(mHourPaintColor);
		mHourLength = 0;
			
			
		mMinutePaint = new Paint();
		mMinutePaint.setAntiAlias(true);
		mMinutePaintWidth = 4;
		mMinutePaint.setStrokeWidth(mMinutePaintWidth);
		mMinutePaintColor = 0xff0000ff;
		mMinutePaint.setColor(mMinutePaintColor);
		mMinuteLength = 0;
		
			
			
		mSecondPaint = new Paint();
		mSecondPaint.setAntiAlias(true);
		mSecondPaintWidth = 2;
		mSecondPaint.setStrokeWidth(mSecondPaintWidth);
		mSecondPaintColor = 0xffff0000;
		mSecondPaint.setColor(mSecondPaintColor);
		mSecondLength = 20;
		
		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);
		mCircleColor = 0xffffffff;
		mCirclePaint.setColor(mCircleColor);
		mRadius = 0;
		
		
		mDialPaint = new Paint();
		mDialPaint.setAntiAlias(true);
		mDialColor = 0xff111111;
		mDialPaint.setColor(mDialColor);
		mDialRectF = new RectF(0, 0, 0, 0);
		
		mDegreePaint = new Paint();
		mDegreePaint.setAntiAlias(true);
		mDegreeColor = 0xFFffffff;
		mDegreePaint.setColor(mDegreeColor);
		mDegreeLength = 0;
		mDegreeWidth = 3;
		mDegreePaint.setStrokeWidth(mDegreeWidth);
		
		mPointRate = (float) 0.4;
		
		picPaint = new Paint();
		picPaint.setAntiAlias(true);
	}
	
	
	public void setHourLength(int len)
	{
		if (len > 0)
		{
			mHourLength = len;
		}
		

	}
	
	public int getHourLength()
	{
		return mHourLength;
	}
	
	public void setMinuteLength(int len)
	{
		if (len > 0)
		{
			mMinuteLength = len;
		}
		

	}
	
	public int getMinuteLength()
	{
		return mMinuteLength;
	}
	
	
	public void setSecondLength(int len)
	{
		if (len > 0)
		{
			mSecondLength = len;
		}	
		

	}
	
	
	public int getSecondLength()
	{
		return mSecondLength;
	}
	
	
	public void setRadius(int radius)
	{
		if (radius > 0)
		{
			mRadius = radius;
		}
	}
	
	public int getRadius()
	{
		return mRadius;
	}
	
	
	public int getHourOffsetLen()
	{
		return (int) (mHourLength * mPointRate);
	}
	
	public int getMinuteOffsetLen()
	{
		return (int) (mMinuteLength * mPointRate);
	}
	
	public int getSecondOffsetLen()
	{
		return (int) (mSecondLength * mPointRate);
	}
	
	public void setCircleColor(int color)
	{
		mCircleColor = color;
		mCirclePaint.setColor(color);
	}
	
	public void setDialColor(int color)
	{
		mDialColor = color;
		mDialPaint.setColor(color);
	}
	
	public void setDialRect(RectF rect)
	{
		mDialRectF = rect;
	}
	
	public void setDegreeLength(int len)
	{
		if (len > 0)
		{
			mDegreeLength = len;
		}
	}
	
	public int getDegreeLength()
	{
		return mDegreeLength;
	}
	
	public RectF getDialRect()
	{
		return mDialRectF;
	}
}
