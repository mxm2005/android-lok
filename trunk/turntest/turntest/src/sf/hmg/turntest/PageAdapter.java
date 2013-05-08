package sf.hmg.turntest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PageAdapter extends BaseAdapter {
	private PageWidget mPageWidget;
	private BookPageFactory pagefactory;
	Bitmap mCurPageBitmap;
	Canvas mCanvas;
	Context context;
	List<Bitmap> bits = new ArrayList<Bitmap>();
	
	public PageAdapter(Context mContext, List<Bitmap> bits) {
		this.bits = bits;
		this.context = mContext;
		pagefactory = new BookPageFactory(540, 320);
		try {
			pagefactory.openbook("/sdcard/test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getCount() {
		return bits.size();
	}

	@Override
	public Object getItem(int position) {
		return bits.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(context, R.layout.main, null);
		mPageWidget = (PageWidget) view.findViewById(R.id.pageWidget);
		
//		mCurPageBitmap =  Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);
//		mNextPageBitmap =  Bitmap.createBitmap(540, 320, Bitmap.Config.ARGB_4444);

		if(position != 0) {
			try {
				pagefactory.nextPage();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		mCanvas = new Canvas(bits.get(position));
		if(position == 0) {
			pagefactory.onDraw(mCanvas);
		}
		mPageWidget.setBitmaps(bits.get(position));
		return mPageWidget;
	}

}
