package sf.hmg.turntest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
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
	int scrollY = 0;
//	private boolean isTop = false;

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
				if(scrollY > getScrollY() && getScrollY() != 0)
				{
					Log.i(TAG, "向上滚动。。。");
					if(onScrollListener != null)
					{
						onScrollListener.onScrollUp();
					}
				}
				else if(scrollY < getScrollY())
				{
					Log.i(TAG, "向下滚。。。。。。。。。。。。。。。。。。。。");
					if(onScrollListener != null)
					{
						onScrollListener.onScrollDown();
					}
				}
				if(view.getMeasuredHeight() <= getScrollY() + getHeight())//底部
				{
					Log.i(TAG, "底部。。。。。。。。。。。。。。。");
					if(onScrollListener != null)
					{
						onScrollListener.onBottom();
					}
				}
				else if(getScrollY() == 0)//顶部
				{
					Log.i(TAG, "顶部。。。。。。。。。。。");
					if(onScrollListener != null)
					{
						onScrollListener.onTop();
					}
				}
				else//滚动 
				{
					scrollY = getScrollY();
					Log.i(TAG, "滚动中。。。。。。。。。。" + scrollY);
					if(onScrollListener != null)
					{
						onScrollListener.onScroll();
					}
				}
			}
		};

	}
	
	float yD = 0;
	float yU = 0;
	OnTouchListener onTouchListener = new OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			switch (event.getAction() & MotionEvent.ACTION_MASK)
			{
			case MotionEvent.ACTION_DOWN:
				yD = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				yU = event.getY();
				if(view != null && onScrollListener != null && yD != yU)
				{
					handler.sendMessageDelayed(handler.obtainMessage(), 200);
				}
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
		 
		void onScrollDown();

		void onTop();

		void onScroll();
		
		void onScrollUp();
	}
}
