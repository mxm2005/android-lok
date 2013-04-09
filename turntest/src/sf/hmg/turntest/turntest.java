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
	private LazyScrollView lazyScrollView;// �Զ���scrollview
	private static int page = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		mPageWidget = new PageWidget(this);
//		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//		setContentView(mPageWidget);
//		mPageWidget = (PageWidget) findViewById(R.id.pageWidget);
//		mPageWidget2 = (PageWidget) findViewById(R.id.pageWidget2);
//		mPageWidget3 = (PageWidget) findViewById(R.id.pageWidget3);
//		mPageWidget2 = new PageWidget(getApplicationContext());
//		mPageWidget2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		mPageWidget3 = new PageWidget(getApplicationContext());
//		mPageWidget3.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		
//		mPageWidget4 = new PageWidget(getApplicationContext());
//		mPageWidget4.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));

//		mCurPageBitmap =  Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		mNextPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		mPageBitmap4 = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		mPageBitmap5 = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		mPageBitmap6 = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		mPageBitmap7 = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);

//		mNextPageCanvas = new Canvas(mNextPageBitmap);

//		pagefactory.setBgBitmap(BitmapFactory.decodeResource(
//				this.getResources(), R.drawable.bg));
		setContentView(R.layout.activity_main);
		
		lazyScrollView = (LazyScrollView) findViewById(R.id.lazyScrollView);
		lazyScrollView.getView();
		lazyScrollView.setOnScrollListener(this);

		lin_all = (LinearLayout) findViewById(R.id.lin_all);
		pagefactory = new BookPageFactory(540, 320);
		


		mPageWidget = new PageWidget(getApplicationContext());
		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
		mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
		mCurPageCanvas = new Canvas(mPageBitmap);
		try {
			pagefactory.openbook("/sdcard/test.txt");
			pagefactory.onDraw(mCurPageCanvas);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Toast.makeText(this, "�����鲻����,�뽫��test.txt������SD����Ŀ¼��",
					Toast.LENGTH_SHORT).show();
		}
		mPageWidget.setBitmaps(mPageBitmap);
		lin_all.addView(mPageWidget, page++);
		
//		while (!pagefactory.islastPage()) {
		for(int i = 0; i < 3; i++) {
			mPageWidget = new PageWidget(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
			mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
			try {
				pagefactory.nextPage();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			mCurPageCanvas = new Canvas(mPageBitmap);
			pagefactory.onDraw(mCurPageCanvas);
			mPageWidget.setBitmaps(mPageBitmap);
			lin_all.addView(mPageWidget, page++);

		}
		
		
		
//		mPageWidget = new PageWidget(getApplicationContext());
//		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		try {
//			pagefactory.nextPage();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		mCurPageCanvas = new Canvas(mPageBitmap);
//		pagefactory.onDraw(mCurPageCanvas);
//		mPageWidget.setBitmaps(mPageBitmap);
//		lin_all.addView(mPageWidget);
//		
//		
//		mPageWidget = new PageWidget(getApplicationContext());
//		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		try {
//			pagefactory.nextPage();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		mCurPageCanvas = new Canvas(mPageBitmap);
//		pagefactory.onDraw(mCurPageCanvas);
//		mPageWidget.setBitmaps(mPageBitmap);
//		lin_all.addView(mPageWidget);
//		
//		
//		
//		mPageWidget = new PageWidget(getApplicationContext());
//		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		try {
//			pagefactory.nextPage();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		mCurPageCanvas = new Canvas(mPageBitmap);
//		pagefactory.onDraw(mCurPageCanvas);
//		mPageWidget.setBitmaps(mPageBitmap);
//		lin_all.addView(mPageWidget);
//		
//		
//		mPageWidget = new PageWidget(getApplicationContext());
//		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		try {
//			pagefactory.nextPage();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		mCurPageCanvas = new Canvas(mPageBitmap);
//		pagefactory.onDraw(mCurPageCanvas);
//		mPageWidget.setBitmaps(mPageBitmap);
//		lin_all.addView(mPageWidget);
//		
//		
//		mPageWidget = new PageWidget(getApplicationContext());
//		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		try {
//			pagefactory.nextPage();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		mCurPageCanvas = new Canvas(mPageBitmap);
//		pagefactory.onDraw(mCurPageCanvas);
//		mPageWidget.setBitmaps(mPageBitmap);
//		lin_all.addView(mPageWidget);
		
//		lin_all.addView(mPageWidget);
//		lin_all.addView(mPageWidget2);
//		lin_all.addView(mPageWidget3);
//		lin_all.addView(mPageWidget4);
		
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

	/* (non-Javadoc)
	 * @see sf.hmg.turntest.LazyScrollView.OnScrollListener#onBottom()
	 */
	@Override
	public void onBottom() {
		Log.i("", "onBottom()...");
		if (mPageBitmap != null && !mPageBitmap.isRecycled()) {
//			mPageBitmap.recycle();
//			mPageBitmap = null;
		}
		
		for(int i = 0; i < 3; i++) {
			mPageWidget = new PageWidget(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
			mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
			try {
				pagefactory.nextPage();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			mCurPageCanvas = new Canvas(mPageBitmap);
			pagefactory.onDraw(mCurPageCanvas);
			mPageWidget.setBitmaps(mPageBitmap);
			if(!pagefactory.islastPage()) {
				lin_all.addView(mPageWidget, page++);
			}
		}
		System.gc();
	}

	@Override
	public void onTop() {
		Log.i("", "onTop()...");
	}

	@Override
	public void onScroll() {
		
	}

	@Override
	public void onScrollUp() {
//		mPageWidget = new PageWidget(getApplicationContext());
//		mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
//		mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		try {
//			pagefactory.prePage();
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
//		mCurPageCanvas = new Canvas(mPageBitmap);
//		pagefactory.onDraw(mCurPageCanvas);
//		mPageWidget.setBitmaps(mPageBitmap);
//		if(!pagefactory.isfirstPage()) {
//			lin_all.addView(mPageWidget, page++);
//		}
	}
}