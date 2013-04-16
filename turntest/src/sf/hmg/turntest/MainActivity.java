/**
 *  Author :  hmg25
 *  Description :
 *//*
package sf.hmg.turntest;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

*//**
 * hmg25's android Type
 *
 *@author yjh
 *
 *//*
public class MainActivity extends Activity {
	private PageWidget mPageWidget;
	Bitmap mCurPageBitmap, mNextPageBitmap;
	Canvas mCurPageCanvas, mNextPageCanvas;
	BookPageFactory pagefactory;
	LinearLayout lists;
	PageAdapter pageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mPageWidget = new PageWidget(getApplicationContext());
		mCurPageBitmap =  Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_4444);
		mNextPageBitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_4444);

		mCurPageCanvas = new Canvas(mCurPageBitmap);
		mNextPageCanvas = new Canvas(mNextPageBitmap);
		pagefactory = new BookPageFactory(50, 50);
		pagefactory.onDraw(mCurPageCanvas);
		try {
			pagefactory.openbook("/sdcard/test.txt");
			pagefactory.onDraw(mCurPageCanvas);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Toast.makeText(this, "电子书不存在,请将《test.txt》放在SD卡根目录下",
					Toast.LENGTH_SHORT).show();
		}
		mPageWidget.setBitmaps(mCurPageBitmap);
//		lists = (LinearLayout) findViewById(R.id.list);
		lists.addView(mPageWidget);
	}
}
*/