/*package sf.hmg.turntest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;

public class PageAdapter extends BaseAdapter {
	private PageWidget mPageWidget;
	private BookPageFactory pagefactory;
	Bitmap mCurPageBitmap;
	Canvas mCanvas;
	Context context;
	List<Vector<String>> bits = new ArrayList<Vector<String>>();
	
	public PageAdapter(Context mContext) {
		this.context = mContext;
		pagefactory = new BookPageFactory(180, 240);
		try {
			pagefactory.openbook("/sdcard/test (2).txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < 140; i++)
		{
			bits.add(pagefactory.pageDown());
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
//		View view = View.inflate(context, R.layout.main, null);
//		mPageWidget = (PageWidget) view.findViewById(R.id.pageWidget);

		PageWidget mPageWidget = new PageWidget(context);
		mPageWidget.setLayoutParams(new LayoutParams(240, 180));
		mPageWidget.setM_lines(bits.get(position));
		return mPageWidget;
	}

}
*/