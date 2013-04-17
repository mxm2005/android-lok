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

/***
 * �Զ���ScrollView
 * 
 * ��������Զ���scrollview
 */
public class LazyScrollView extends HorizontalScrollView  implements android.widget.AdapterView.OnItemClickListener
{
	private static final String TAG = "LazyScrollView";
	private Handler handler;
	private View view;
	private float beforeLenght;//����ָ����
	float xD = 0;
	float xU = 0;
	int scrollX = 0;

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
	}

	public LazyScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	/***
	 * ��ʼ��
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
				if(scrollX > getScrollX() && getScrollX() != 0)
				{
//					Log.i(TAG, "���Ϲ���������");
					if(onScrollListener != null)
					{
						onScrollListener.onScrollUp();
					}
				}
				else if(scrollX < getScrollX())
				{
//					Log.i(TAG, "���¹�����������������������������������������");
					if(onScrollListener != null)
					{
						onScrollListener.onScrollDown();
					}
				}
				if(view.getMeasuredWidth() <= getScrollX() + getWidth())
				{
//					Log.i(TAG, "�ײ�������������������������������");
					if(onScrollListener != null)
					{
						onScrollListener.onBottom();
					}
				}
				else if(getScrollX() == 0)
				{
//					Log.i(TAG, "��������������������������");
					if(onScrollListener != null)
					{
						onScrollListener.onTop();
					}
				}
				else
				{
					scrollX = getScrollX();
//					Log.i(TAG, "�����С�������������������");
					if(onScrollListener != null)
					{
						onScrollListener.onScroll();
					}
				}
			}
		};

		
		this.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				view = getChildAt(position);
			}
		});
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
			beforeLenght = getDistance(event);// ��ȡ����ľ���
//			Log.d(TAG, "two finger distance is " + beforeLenght);
		}
	}
	
	float getDistance(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/**
	 * ��òο���View����Ҫ��Ϊ�˻������MeasuredHeight��Ȼ��͹�������ScrollY+getHeight���Ƚϡ�
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
	 * ����ӿ�
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
		public void onItemClick(AdapterView<?> parent, View view, int position, long id);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		onItemClickListener.onItemClick(parent, view, position, id);
	}
}
