package com.c35.toast;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class MyToast {
	private static final String TAG = "MyToast";
	public static final int LENGTH_MAX = -1; // show until hide() function invoked
	boolean mCanceled = true;
	Handler mHandler;
	Context mContext;
	Toast mToast;
	public MyToast(Context context) {
		this(context, new Handler());
	}
	public MyToast(Context context, Handler h) {
		mContext = context;
		mHandler = h;
		mToast = Toast.makeText(mContext.getApplicationContext(), "", Toast.LENGTH_SHORT);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		/*ImageView imageCodeProject = new ImageView(mContext.getApplicationContext());
		imageCodeProject.setImageResource(R.drawable.icon);*/
		LinearLayout toastView = (LinearLayout) mToast.getView();
		LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
		View layout = inflater.inflate(R.layout.newlayout, (ViewGroup) ((Activity) mContext).findViewById(R.id.newtoast));
		ImageView image = (ImageView) layout.findViewById(R.id.image);
		image.setImageResource(R.drawable.icon);
		TextView sendtime = (TextView) layout.findViewById(R.id.sendtime);
		sendtime.setText("19:45");
		TextView time = (TextView) layout.findViewById(R.id.time);
		time.setText("2011-3-21");
		TextView title = (TextView) layout.findViewById(R.id.title);
		title.setText("发件人：刘杰\n<liujie8642@163.com>\n"+"主题："+StringUtil.parseString("dsadsadsad今天发大财今天发大财今天发大财今天发大财今天发大财今天发大财", 19));
//		title.setText("kiujiedsadsadsadsadsadww");
//		title.setWidth(200);
//		title.setGravity(Gravity.FILL);
//		title.setTextSize(22);
		Log.d(TAG, "title.getTextColors"+title.getTextColors());
//		title.setTextColor(R.color.white);
//		title.setTextColor(R.color.white);
		/*TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
		text.setText("今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!今天发大财!");*/
//		toastView.setBackgroundDrawable(mContext.peekWallpaper());
		toastView.addView(layout, 0);
	}
	public void show(int resId, int duration) {
		if (duration != LENGTH_MAX) {
			mToast.setDuration(duration);
			mToast.show();
		} else if (mCanceled) {
			mToast.setDuration(Toast.LENGTH_LONG);
			mCanceled = false;
			showUntilCancel();
		}
	}
	public void show(String text, int duration) {
		mToast.setText(text);
		if (duration != LENGTH_MAX) {
			mToast.setDuration(duration);
			mToast.show();
		} else if (mCanceled) {
			mToast.setDuration(Toast.LENGTH_LONG);
			mCanceled = false;
			showUntilCancel();
		}
	}
	private void showUntilCancel() {
		if (mCanceled)
			return;
		mToast.show();
		mHandler.postDelayed(new Runnable() {
			public void run() {
				showUntilCancel();
			}
		}, 3000);
	}
	public void hide() {
		Log.d(TAG, "hide");
		mToast.cancel();
		mCanceled = true;
	}
	public boolean isShowing() {
		return !mCanceled;
	}
}
