package com.jj.waterfall;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/***
 * 自定义ScrollView
 * 
 * @author zhangjia
 * 
 */
public class LazyScrollView extends ScrollView 
{
	private static final String TAG = "LazyScrollView";
	private Handler handler;
	private View view;
	private float beforeLenght;//两手指距离

	private OnScrollListener onScrollListener;

	public void setOnScrollListener(OnScrollListener onScrollListener)
	{
		this.onScrollListener = onScrollListener;
	}

	public LazyScrollView(Context context)
	{
		super(context);
	}

	public LazyScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public LazyScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	// 这个获得总的高度
	@Override
	public int computeVerticalScrollRange()
	{
		return super.computeHorizontalScrollRange();
	}

	@Override
	public int computeVerticalScrollOffset()
	{
		return super.computeVerticalScrollOffset();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/***
	 * 初始化
	 */
	private void init()
	{

		this.setOnTouchListener(onTouchListener);
		handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				// process incoming messages here
				super.handleMessage(msg);
//				switch (msg.what)
//				{
//				case 1:
					if(view.getMeasuredHeight() <= getScrollY() + getHeight())
					{
						Log.i(TAG, "scrolly + height > ...");
						if(onScrollListener != null)
						{
							onScrollListener.onBottom();
						}

					}
					else if(getScrollY() == 0)
					{
						Log.i(TAG, "scrolly is zero");
						if(onScrollListener != null)
						{
							onScrollListener.onTop();
						}
					}
					else
					{
						Log.i(TAG, "else.");
						if(onScrollListener != null)
						{
							onScrollListener.onScroll();
						}
					}
//					break;
//				default:
//					break;
//				}
			}
		};

	}
	
	OnTouchListener onTouchListener = new OnTouchListener()
	{

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			// TODO Auto-generated method stub
			switch (event.getAction() & MotionEvent.ACTION_MASK)
			{
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP:
				if(view != null && onScrollListener != null)
				{
					handler.sendMessageDelayed(handler.obtainMessage(), 200);
				}
				break;
				
//			// 多点触摸
//			case MotionEvent.ACTION_POINTER_DOWN:
//				Log.i(TAG, "action_pointer_down");
//				onPointerDown(event);
//				FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) view.getLayoutParams();
//				linearParams.width = FrameLayout.LayoutParams.FILL_PARENT;
//				view.setLayoutParams(linearParams);
//				return true;
//			// 多点松开
//			case MotionEvent.ACTION_POINTER_UP:
//				FrameLayout.LayoutParams linearParamss = (FrameLayout.LayoutParams) view.getLayoutParams();
//				linearParamss.width = FrameLayout.LayoutParams.FILL_PARENT;
//				view.setLayoutParams(linearParamss);
//				return true;
			case MotionEvent.ACTION_MOVE:
//				onTouchMove(event);
//				Log.i(TAG, "action_move..");
				break;
			default:
				break;
			}
			return false;
		}

	};
	
	void onPointerDown(MotionEvent event)
	{
		if(event.getPointerCount() == 2)
		{
			beforeLenght = getDistance(event);// 获取两点的距离
			Log.d(TAG, "two finger distance is " + beforeLenght);
		}
	}
	
	float getDistance(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/**
	 * 获得参考的View，主要是为了获得它的MeasuredHeight，然后和滚动条的ScrollY+getHeight作比较。
	 */
	public void getView()
	{
		this.view = getChildAt(0);
		if(view != null)
		{
			init();
		}
	}

	/**
	 * 定义接口
	 * 
	 * @author admin
	 * 
	 */
	public interface OnScrollListener
	{
		void onBottom();

		void onTop();

		void onScroll();
	}
}
