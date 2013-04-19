package sf.hmg.turntest;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class read extends Activity {
	private ReadView mPageWidget;
	BookPageFactory2 pagefactory;
	private int page = 0;
	private ArrayList<View> pageViews;
	private ViewPager guidePages; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.read);
		
		guidePages = (ViewPager) findViewById(R.id.guidePages);
		pagefactory = new BookPageFactory2(960, 540);
		mPageWidget = new ReadView(getApplicationContext());
		mPageWidget.setLayoutParams(new LayoutParams(960, 540));
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

        pageViews = new ArrayList<View>();
		pageViews.add(mPageWidget);
		while (!pagefactory.islastPage()) {
			mPageWidget = new ReadView(getApplicationContext());
			mPageWidget.setLayoutParams(new LayoutParams(960, 540));
			mPageWidget.setScaleType(ScaleType.FIT_XY);
			mPageWidget.setM_lines(pagefactory.pageDown());
			mPageWidget.setM_mbBufBegin(pagefactory.getM_mbBufBegin());
			mPageWidget.setTag(page++);
			pageViews.add(mPageWidget);
		}
		
		guidePages.setAdapter(new GuidePageAdapter());  
		guidePages.setCurrentItem(getIntent().getIntExtra("po", 0));
	}
	
	private class GuidePageAdapter extends PagerAdapter {
	  	  
        @Override  
        public int getCount() {
            return pageViews.size();
        }
  
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
  
        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }
  
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).addView(pageViews.get(arg1));  
            return pageViews.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
    } 
	
}