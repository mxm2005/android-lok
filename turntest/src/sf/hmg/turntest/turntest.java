package sf.hmg.turntest;

import java.io.IOException;

import sf.hmg.turntest.LazyScrollView.OnItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class turntest extends Activity implements LazyScrollView.OnScrollListener, OnItemClickListener {
	private PageWidget mPageWidget;
	BookPageFactory pagefactory;
	private LinearLayout lin_all;
	private LazyScrollView lazyScrollView;// 自定义scrollview
	private int page = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		lazyScrollView = (LazyScrollView) findViewById(R.id.lazyScrollView);
		lazyScrollView.getView();
		lazyScrollView.setOnScrollListener(this);
		lazyScrollView.setOnItemClickListener(this);
		lazyScrollView.setFillViewport(true);
		
		lin_all = (LinearLayout) findViewById(R.id.lin_all);
		pagefactory = new BookPageFactory(240, 180);

		mPageWidget = new PageWidget(getApplicationContext());
		mPageWidget.setLayoutParams(new LayoutParams(240, 180));
		mPageWidget.setScaleType(ScaleType.FIT_XY);
		try {
			pagefactory.openbook("/sdcard/test (2).txt");
			mPageWidget.setM_lines(pagefactory.pageDown());
			mPageWidget.setM_mbBufLen(pagefactory.getM_mbBufLen());
		} catch (IOException e1) {
			e1.printStackTrace();
			Toast.makeText(this, "电子书不存在,请将《test.txt》放在SD卡根目录下",
					Toast.LENGTH_SHORT).show();
		}
		mPageWidget.setTag(page++);
		lin_all.addView(mPageWidget);
		
		for(int i = 0; i < 6; i++) {
			mPageWidget = new PageWidget(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(240, 180));
			mPageWidget.setScaleType(ScaleType.FIT_XY);
			mPageWidget.setM_lines(pagefactory.pageDown());
			mPageWidget.setM_mbBufBegin(pagefactory.getM_mbBufBegin());
			mPageWidget.setTag(page++);
			lin_all.addView(mPageWidget);
			lin_all.bringChildToFront(mPageWidget);
		}
	}
	
	private boolean isFinish = false;

	@Override
	public void onBottom() {
//		Log.i("", "onBottom()...");
		
		for(int i = 0; i < 6; i++) {
			mPageWidget = new PageWidget(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(240, 180));
			mPageWidget.setScaleType(ScaleType.FIT_XY);
			mPageWidget.setM_lines(pagefactory.pageDown());
			mPageWidget.setM_mbBufBegin(pagefactory.getM_mbBufBegin());
			mPageWidget.setTag(page++);
			if(pagefactory.islastPage() && !isFinish) {
				isFinish = true;
				lin_all.addView(mPageWidget);
				lin_all.bringChildToFront(mPageWidget);
			}
			if(!isFinish)
			{
				lin_all.addView(mPageWidget);
				lin_all.bringChildToFront(mPageWidget);
			}
		}
	}

	@Override
	public void onTop() {
//		Log.d("", "onTop()....");
	}

	@Override
	public void onScroll() {
//		Log.d("", "onScroll()....");
	}

	@Override
	public void onScrollUp() {
//		Log.d("", "onScrollUp()....");
	}
	
	@Override
	public void onScrollDown() {
//		Log.d("", "onScrollDown()....");
	}

	@Override
	public void onItemClick(View view, int position) {
		
		System.out.println("onItemClick............................................................." + " positoin: " + position);
		Intent intent = new Intent(turntest.this, read.class);
		intent.putExtra("po", position);
		startActivity(intent);
	}
	
}