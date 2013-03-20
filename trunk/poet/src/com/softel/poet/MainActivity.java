package com.softel.poet;

import java.util.ArrayList;
import java.util.List;

import com.softel.poet.util.ToastUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher.ViewFactory;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity implements OnItemSelectedListener, ViewFactory
{
	private List<ObjMap> lists = new ArrayList<ObjMap>();
//	private Integer[] mThumbIds = { R.drawable.b, R.drawable.z, R.drawable.z, R.drawable.z, R.drawable.z, R.drawable.z, 
//			R.drawable.c, R.drawable.z, R.drawable.z, R.drawable.z, R.drawable.z, R.drawable.z, 
//			R.drawable.d, R.drawable.z, R.drawable.z, R.drawable.z, R.drawable.z, R.drawable.z, 
//			R.drawable.e, R.drawable.z, R.drawable.z, R.drawable.z,	R.drawable.z, R.drawable.z, 
//			R.drawable.f, R.drawable.z, R.drawable.z, R.drawable.z,	R.drawable.z, R.drawable.z, 
//			R.drawable.g, R.drawable.z, R.drawable.z, R.drawable.z,	R.drawable.z, R.drawable.z };
	private Gallery gallery;
	private ImageSwitcher is;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ImageView) findViewById(R.id.imageView);
		
		lists.add(new ObjMap(R.drawable.b, "chapter", R.drawable.b, 1));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		
		
		lists.add(new ObjMap(R.drawable.c, "chapter", R.drawable.c, 2));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		
		
		lists.add(new ObjMap(R.drawable.d, "chapter", R.drawable.d, 3));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		
		lists.add(new ObjMap(R.drawable.e, "chapter", R.drawable.e, 4));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		
		lists.add(new ObjMap(R.drawable.f, "chapter", R.drawable.f, 5));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		
		lists.add(new ObjMap(R.drawable.g, "chapter", R.drawable.g, 6));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		
		gallery = (Gallery) findViewById(R.id.gallery);

		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemSelectedListener(this);
		gallery.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
//				imageView.setVisibility(View.VISIBLE);
//				gallery.setVisibility(View.GONE);
//				is.setVisibility(View.GONE);
//				imageView.setImageResource(mThumbIds[position]);
			}
		});
		
		is = (ImageSwitcher) findViewById(R.id.switcher);
		is.setFactory(this);
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
			return lists.size();
		}

		public Object getItem(int position)
		{
			return position;
		}

		public long getItemId(int position)
		{
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			ImageView i = new ImageView(mContext);

			i.setImageResource(lists.get(position).getDrawable());
			i.setAdjustViewBounds(true);
			i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			
//			final TextView tv = new TextView(mContext);
//			tv.setText(position + "-");
//			tv.setTextSize(50);
//			tv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//			tv.setOnClickListener(new OnClickListener()
//			{
//				
//				@Override
//				public void onClick(View v)
//				{
//					tv.setText("我被点击了");
////					notifyDataSetChanged();
//				}
//			});
////			i.setBackgroundResource(R.drawable.e);
			return i;
		}
	}

	int previousChapter = 0;
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	{
		ObjMap oMap = lists.get(position);
		String currentType = oMap.getType();
		int currentChapter = oMap.getChapter();
		if("chapter".equals(currentType))
		{
			Log.i("", "chapter item.");
			is.setImageResource(oMap.getDrawable());
		}
		else if("page".equals(currentType))
		{
			Log.i("", "page item.");
			if(previousChapter != currentChapter)
			{
				Log.i("", "the different page setTile");
				is.setImageResource(oMap.getTitle());
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
//		is.setImageResource(mThumbIds[position]);
//		Toast.makeText(getApplicationContext(), position + "-", Toast.LENGTH_SHORT).show();
		ToastUtil.showTextToast(getApplicationContext(), String.valueOf(position + 1));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
		
	}

	@Override
	public View makeView()
	{
		ImageView i = new ImageView(this);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return i;
	}

}

class ObjMap
{
	int drawable;
	String type;
	int title;
	int chapter;
	
	ObjMap(int drawable, String type, int title, int chapter)
	{
		this.drawable = drawable;
		this.type = type;
		this.title = title;
		this.chapter = chapter;
	}

	public int getDrawable()
	{
		return drawable;
	}

	public void setDrawable(int drawable)
	{
		this.drawable = drawable;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getTitle()
	{
		return title;
	}

	public void setTitle(int title)
	{
		this.title = title;
	}

	public int getChapter()
	{
		return chapter;
	}

	public void setChapter(int chapter)
	{
		this.chapter = chapter;
	}
	
}