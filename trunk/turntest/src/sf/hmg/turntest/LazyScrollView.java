package sf.hmg.turntest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

/***
 * 自定义ScrollView
 * 
 * 横向滚动自定义scrollview
 */
public class LazyScrollView extends HorizontalScrollView
{
	private static final String TAG = "LazyScrollView";
	private Handler handler;
	private View view;
	private float beforeLenght;//两手指距离
	float xD = 0;
	float xU = 0;
	int scrollX = 0;
	Context c;

	private OnScrollListener onScrollListener;
	
	private OnItemClickListener onItemClickListener;

	public void setOnScrollListener(OnScrollListener onScrollListener)
	{
		this.onScrollListener = onScrollListener;
	}
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener)
	{
		this.onItemClickListener = onItemClickListener;
	}

	public LazyScrollView(Context context)
	{
		super(context);
	}

	public LazyScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		c = context;
	}

	public LazyScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
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
				switch (msg.what) {
				case 1:
					if(scrollX > getScrollX() && getScrollX() != 0)
					{
//						Log.i(TAG, "向上滚动。。。");
						if(onScrollListener != null)
						{
							onScrollListener.onScrollUp();
						}
					}
					else if(scrollX < getScrollX())
					{
//						Log.i(TAG, "向下滚。。。。。。。。。。。。。。。。。。。。");
						if(onScrollListener != null)
						{
							onScrollListener.onScrollDown();
						}
					}
					if(view.getMeasuredWidth() <= getScrollX() + getWidth())
					{
//						Log.i(TAG, "底部。。。。。。。。。。。。。。。");
						if(onScrollListener != null)
						{
							onScrollListener.onBottom();
						}
					}
					else if(getScrollX() == 0)
					{
//						Log.i(TAG, "顶部。。。。。。。。。。。");
						if(onScrollListener != null)
						{
							onScrollListener.onTop();
						}
					}
					else
					{
						scrollX = getScrollX();
//						Log.i(TAG, "滚动中。。。。。。。。。。");
						if(onScrollListener != null)
						{
							onScrollListener.onScroll();
						}
					}
					break;

				case 2:
					int position = ((int) (computeHorizontalScrollOffset() + xD)) / 240;
					View childView = getChildAt(position);
//					TextView childView = new TextView(c);
//					childView.setText("ddd");
//					if(onItemClickListener !=null && childView != null)
						onItemClickListener.onItemClick(childView, position);
					break;
				default:
					break;
				}
			}
		};

	}
	
	
	OnTouchListener onTouchListener = new OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			switch (event.getAction() & MotionEvent.ACTION_MASK)
			{
			case MotionEvent.ACTION_DOWN:
				xD = event.getX();
				break;
			case MotionEvent.ACTION_UP:
				xU = event.getX();
				if(view != null && onScrollListener != null && xD != xU)
				{
//					handler.sendMessageDelayed(handler.obtainMessage(), 200);
					handler.sendMessageDelayed(Message.obtain(handler, 1), 200);
				}
				else if(view != null && onScrollListener != null  && xD == xU)
				{
					handler.sendMessageDelayed(Message.obtain(handler, 2), 200);
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
//			Log.d(TAG, "two finger distance is " + beforeLenght);
		}
	}
	
	float getDistance(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}
	
	@Override
	public View getChildAt(int index) {
		return super.getChildAt(index);
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
	
	public interface OnItemClickListener
	{
		public void onItemClick(View view, int position);
	}

}
