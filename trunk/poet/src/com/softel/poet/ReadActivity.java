package com.softel.poet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ReadActivity extends Activity implements OnTouchListener, OnGestureListener
{
	private ViewFlipper viewFlipper;
	private ImageView iv;
	private ImageView[] ivs;
	private GestureDetector gd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		
		Intent intent = getIntent();
		int number = intent.getIntExtra("number", 0);
		Log.i("", "number : " + number);
		addView();
		viewFlipper.setOnTouchListener(this);
		viewFlipper.setLongClickable(true);//设置ViewFlipper长按有效  
		viewFlipper.setDisplayedChild(number);
		gd = new GestureDetector(this);//手势事件的注册，一定要将滑动事件交给他处理啦，不然很麻烦
	}
	
	private void addView()
	{
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		int count = Photo.lists.size();
		ivs = new ImageView[count];
		viewFlipper.removeAllViews();
		for(int i = 0; i < count; i++)
		{
			iv = new ImageView(ReadActivity.this);
			ivs[i] = iv;
			ivs[i].setImageResource(Photo.lists.get(i).getDrawable());
			viewFlipper.addView(ivs[i]);
		}
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		if(e1.getX() - e2.getX() > 100)
		{
			viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
					ReadActivity.this, R.anim.left_in));
			viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
					ReadActivity.this, R.anim.left_out));
			viewFlipper.showNext();
		}
		else if(e2.getX() - e1.getX() > 100)
		{
			viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
					ReadActivity.this, R.anim.right_in));
			viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
					ReadActivity.this, R.anim.right_out));
			viewFlipper.showPrevious();
		}
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return gd.onTouchEvent(event);//注意这里，就是要把事件交给GestureDetector处理，这样会简单很多
	}
}
