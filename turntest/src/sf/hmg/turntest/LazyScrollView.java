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
 * �Զ���ScrollView
 * 
 * @author zhangjia
 * 
 */
public class LazyScrollView extends ScrollView 
{
	private static final String TAG = "LazyScrollView";
	private Handler handler;
	private View view;
	private float beforeLenght;//����ָ����

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

	// �������ܵĸ߶�
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
				if(scrollY > getScrollY() && getScrollY() != 0)
				{
					Log.i(TAG, "���Ϲ���������");
					if(onScrollListener != null)
					{
						onScrollListener.onScrollUp();
					}
				}
				else if(scrollY < getScrollY())
				{
					Log.i(TAG, "���¹�����������������������������������������");
					if(onScrollListener != null)
					{
						onScrollListener.onScrollDown();
					}
				}
				if(view.getMeasuredHeight() <= getScrollY() + getHeight())//�ײ�
				{
					Log.i(TAG, "�ײ�������������������������������");
					if(onScrollListener != null)
					{
						onScrollListener.onBottom();
					}
				}
				else if(getScrollY() == 0)//����
				{
					Log.i(TAG, "��������������������������");
					if(onScrollListener != null)
					{
						onScrollListener.onTop();
					}
				}
				else//���� 
				{
					scrollY = getScrollY();
					Log.i(TAG, "�����С�������������������" + scrollY);
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
			beforeLenght = getDistance(event);// ��ȡ����ľ���
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
}
