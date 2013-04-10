package sf.hmg.turntest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	private static int page = 0;
//	LCache lc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
//		lc = new LCache();
		
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
			Toast.makeText(this, "电子书不存在,请将《test.txt》放在SD卡根目录下",
					Toast.LENGTH_SHORT).show();
		}
		mPageWidget.setBitmaps(mPageBitmap);
//		try {
//			saveMyBitmap(mPageBitmap, String.valueOf(page));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		lc.put("", mPageBitmap);
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
//			try {
//				saveMyBitmap(mPageBitmap, String.valueOf(page));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			lc.put("", mPageBitmap);
			lin_all.addView(mPageWidget, page++);

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
		if (mPageBitmap != null && !mPageBitmap.isRecycled()) {
//			mPageBitmap.recycle();
//			mPageBitmap = null;
		}
		
		for(int i = 0; i < 6; i++) {
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
//				try {
//					saveMyBitmap(mPageBitmap, String.valueOf(page));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				lc.put("", mPageBitmap);
				lin_all.addView(mPageWidget, page++);
			}
			int scrollY = lazyScrollView.getScrollY();
			int pageNumber = scrollY / 320 + 2;
			System.out.println("pageNumber: " + pageNumber + "\n" + "scrollY: " + scrollY + "\n");
//			for(int j = 0; j < 3; j++) {
//				lin_all.removeViewAt(pageNumber--);
//			}
		}
	}

	@Override
	public void onTop() {
		Log.i("", "onTop()...");
	}

	@Override
	public void onScroll() {
		Log.i("count.", "onScrol................................l");
	}

	@Override
	public void onScrollUp() {
		int scrollY = lazyScrollView.getScrollY();
		int page = scrollY / 320;
		for(int i = 0; i < 3; i++) {
			mPageWidget = new PageWidget(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 320));
			mPageBitmap = Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
			try {
				pagefactory.prePage();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			mCurPageCanvas = new Canvas(mPageBitmap);
			pagefactory.onDraw(mCurPageCanvas);
			mPageWidget.setBitmaps(mPageBitmap);
			if(!pagefactory.isfirstPage()) {
				lin_all.addView(mPageWidget, page--);
			}
		}
	}
	
	public void saveMyBitmap(Bitmap mBitmap, String bitName) throws IOException {
		File f = new File("/mnt/sdcard/softel/" + bitName + ".png");
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Bitmap readBitMap(String path) throws FileNotFoundException {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
//		InputStream is = context.getResources().openRawResource(resId);
		File file = new File(path);
		InputStream is = new FileInputStream("/mnt/sdcard/softel/" + file + ".png");
		return BitmapFactory.decodeStream(is, null, opt);
	}
}