package sf.hmg.turntest;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class turntest extends Activity implements LazyScrollView.OnScrollListener{
	private PageWidget mPageWidget/*, mPageWidget2, mPageWidget3, mPageWidget4*/;
//	Bitmap mCurPageBitmap, mNextPageBitmap, mPageBitmap, mPageBitmap4, mPageBitmap5, mPageBitmap6, mPageBitmap7;
	Bitmap mPageBitmap = null;
	Canvas mCurPageCanvas/*, mNextPageCanvas*/;
	BookPageFactory pagefactory;
	private LinearLayout lin_all;
	private LazyScrollView lazyScrollView;// 自定义scrollview

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

		lin_all = (LinearLayout) findViewById(R.id.lin_all);
		pagefactory = new BookPageFactory(540, 320);

		mPageWidget = new PageWidget(getApplicationContext());
		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
		try {
			pagefactory.openbook("/sdcard/test.txt");
			mPageWidget.setM_lines(pagefactory.pageDown());
		} catch (IOException e1) {
			e1.printStackTrace();
			Toast.makeText(this, "电子书不存在,请将《test.txt》放在SD卡根目录下",
					Toast.LENGTH_SHORT).show();
		}
		lin_all.addView(mPageWidget, 0);
		
		for(int i = 0; i < 6; i++) {
			mPageWidget = new PageWidget(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
			mPageWidget.setM_lines(pagefactory.pageDown());
			lin_all.addView(mPageWidget);
			lin_all.bringChildToFront(mPageWidget);
		}
		
		
//		mPageWidget.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent e) {
//				// TODO Auto-generated method stub
//				boolean ret = false;
//				if (v == mPageWidget) {
//					if (e.getAction() == MotionEvent.ACTION_DOWN) {
//						mPageWidget.abortAnimation();
//						mPageWidget.calcCornerXY(e.getX(), e.getY());
//
//						pagefactory.onDraw(mCurPageCanvas);
//						if (mPageWidget.DragToRight()) {
//							try {
//								pagefactory.prePage();
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//							if (pagefactory.isfirstPage())
//								return false;
//							pagefactory.onDraw(mNextPageCanvas);
//						} else {
//							try {
//								pagefactory.nextPage();
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//							if (pagefactory.islastPage())
//								return false;
//							pagefactory.onDraw(mNextPageCanvas);
//						}
//						mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
//					}
//                 
//					 ret = mPageWidget.doTouchEvent(e);
//					return ret;
//				}
//				return false;
//			}
//
//		});
	}

	@Override
	public void onBottom() {
		Log.i("", "onBottom()...");
		
		for(int i = 0; i < 6; i++) {
			mPageWidget = new PageWidget(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
			mPageWidget.setM_lines(pagefactory.pageDown());
			if(!pagefactory.islastPage()) {
				lin_all.addView(mPageWidget);
				lin_all.bringChildToFront(mPageWidget);
			}
		}
	}

	@Override
	public void onTop() {
	}

	@Override
	public void onScroll() {
	}

	@Override
	public void onScrollUp() {
	}
	
	@Override
	public void onScrollDown() {
	}
	
}