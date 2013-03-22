package com.softel.poet;

import com.softel.poet.bean.ObjMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnItemSelectedListener, OnClickListener, OnTouchListener, OnGestureListener
{
	private Gallery gallery;
	private ImageView iv;
	private ImageView[] ivs;
    private GestureDetector gd;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		gallery = (Gallery) findViewById(R.id.gallery);

		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemSelectedListener(this);
		gallery.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Log.i("", "onItemClick..");
				Intent intent = new Intent(MainActivity.this, ReadActivity.class);
				intent.putExtra("number", position);
				startActivity(intent);
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
		});
		addView();
		addPoint();
		
		viewFlipper.setOnTouchListener(this);
		viewFlipper.setLongClickable(true);//设置ViewFlipper长按有效  
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
			if("chapter".equals(((ObjMap)Photo.lists.get(i)).getType()))
			{
				iv = new ImageView(MainActivity.this);
				ivs[i] = iv;
				ivs[i].setImageResource(Photo.lists.get(i).getDrawable());
				viewFlipper.addView(ivs[i]);
			}
		}
	}
	
	private ViewFlipper viewFlipper;
	private int mViewCount;
	private ImageView[] mImageViews;
	private ViewGroup group;
	private ImageView imageView;
	private int mCurSel;
	
	private void addPoint() {
        mViewCount = viewFlipper.getChildCount();
        Log.i("", "数量：  " +  mViewCount);
        mImageViews = new ImageView[mViewCount];
        group = (ViewGroup) findViewById(R.id.viewGroup);
        group.removeAllViews();
        for (int i = 0; i < mViewCount; i++) {
            imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new LayoutParams(20, 20));
            imageView.setPadding(20, 0, 20, 0);
            imageView.setTag(i);
            imageView.setFocusable(false);
            imageView.setFocusableInTouchMode(false);
//            imageView.setOnClickListener(this);
            mImageViews[i] = imageView;
            mImageViews[i].setBackgroundResource(R.drawable.guide_round);
            group.addView(mImageViews[i]);
        }
        mCurSel = 0;
        mImageViews[mCurSel].setEnabled(false);// enable=false时为黑。。。
    }

	public class ImageAdapter extends BaseAdapter
	{
		private Context mContext;
		
		public ImageAdapter(Context c)
		{
			mContext = c;
		}

		public int getCount()
		{
			return Photo.lists.size();
		}

		public Object getItem(int position)
		{
			return position;
		}

		public long getItemId(int position)
		{
			return position;
		}
		

		public View getView(final int position, View convertView, ViewGroup parent)
		{
			ImageView i = new ImageView(mContext);

			i.setImageResource(Photo.lists.get(position).getDrawable());
			i.setAdjustViewBounds(true);
			i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			return i;
		}
	}
	
	private void setCurPoint(int pos) {
        if (pos < 0 || pos > mViewCount - 1 || mCurSel == pos) {
            return;
        }
        if (mCurSel > pos) {
        	viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
        			MainActivity.this, R.anim.right_in));
        	viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
        			MainActivity.this, R.anim.right_out));
        	gallery.setAnimation(AnimationUtils.loadAnimation(
        			MainActivity.this, R.anim.right_in));
            viewFlipper.showNext();
        } else if (pos > mCurSel) {
        	viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
        			MainActivity.this, R.anim.left_in));
        	viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
        			MainActivity.this, R.anim.left_out));
        	gallery.setAnimation(AnimationUtils.loadAnimation(
        			MainActivity.this, R.anim.left_in));
            viewFlipper.showPrevious();
        }
        mImageViews[mCurSel].setEnabled(true);// 点击后把enable设为true，为白
        mImageViews[pos].setEnabled(false);
        mCurSel = pos;
    }
	
	private void setPoint(int pos)
	{
		mImageViews[mCurSel].setEnabled(true);
		mImageViews[pos].setEnabled(false);
		mCurSel = pos;
	}

	int previousChapter = 0;
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	{
		ObjMap oMap = Photo.lists.get(position);
		String currentType = oMap.getType();
		int currentChapter = oMap.getChapter();
		if("chapter".equals(currentType))
		{
			Log.i("", "chapter item.");
			viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
					MainActivity.this, R.anim.left_in));
			viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
					MainActivity.this, R.anim.left_out));
			viewFlipper.setDisplayedChild(Photo.lists.get(position).getChapter());
			setPoint(Photo.lists.get(position).getChapter());
		}
		else if("page".equals(currentType))
		{
			Log.i("", "page item.");
			if(previousChapter != currentChapter)
			{
				Log.i("", "the different page setTile");
				viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.right_in));
				viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.right_out));
				viewFlipper.setDisplayedChild(Photo.lists.get(position).getChapter());
				setPoint(Photo.lists.get(position).getChapter());
			}
			else
			{
				Log.i("", "the same page setTile");
			}
		}
		else
		{
			return;
		}
		previousChapter = currentChapter;
//		ToastUtil.showTextToast(getApplicationContext(), String.valueOf(position + 1));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
		
	}
	

	@Override
	public void onClick(View v)
	{
		int pos = (Integer) (v.getTag());
        setCurPoint(pos);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return gd.onTouchEvent(event);//注意这里，就是要把事件交给GestureDetector处理，这样会简单很多
	}

	@Override
	public boolean onDown(MotionEvent e)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		Log.i("", "onFling.......................");
		if(e1.getX() - e2.getX() > 100)
		{
			setCurPoint(mCurSel + 1);
		}
		else if(e2.getX() - e1.getX() > 100)
		{
			setCurPoint(mCurSel - 1);
		}
		gallery.setSelection(mCurSel * 6);
		return false;
	}

}

