package com.softel.poet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<Map<String, ObjMap>> lists = new ArrayList<Map<String, ObjMap>>();
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
		
		Map<String, ObjMap> map = new HashMap<String, ObjMap>();
		map.put("type", new ObjMap(R.drawable.b, "chapter", R.drawable.b, 1));
		lists.add(map);
		Map<String, ObjMap> map11 = new HashMap<String, ObjMap>();
		map11.put("type", new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(map11);
		Map<String, ObjMap> map12 = new HashMap<String, ObjMap>();
		map12.put("type", new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(map12);
		Map<String, ObjMap> map13 = new HashMap<String, ObjMap>();
		map13.put("type", new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(map13);
		Map<String, ObjMap> map14 = new HashMap<String, ObjMap>();
		map14.put("type", new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(map14);
		Map<String, ObjMap> map15 = new HashMap<String, ObjMap>();
		map15.put("type", new ObjMap(R.drawable.z, "page", R.drawable.b, 1));
		lists.add(map15);
		
		
		Map<String, ObjMap> map1 = new HashMap<String, ObjMap>();
		map1.put("type", new ObjMap(R.drawable.c, "chapter", R.drawable.c, 2));
		lists.add(map1);
		Map<String, ObjMap> map21 = new HashMap<String, ObjMap>();
		map21.put("type", new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(map21);
		Map<String, ObjMap> map22 = new HashMap<String, ObjMap>();
		map22.put("type", new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(map22);
		Map<String, ObjMap> map23 = new HashMap<String, ObjMap>();
		map23.put("type", new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(map23);
		Map<String, ObjMap> map24 = new HashMap<String, ObjMap>();
		map24.put("type", new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(map24);
		Map<String, ObjMap> map25 = new HashMap<String, ObjMap>();
		map25.put("type", new ObjMap(R.drawable.z, "page", R.drawable.c, 2));
		lists.add(map25);
		
		
		Map<String, ObjMap> map2 = new HashMap<String, ObjMap>();
		map2.put("type", new ObjMap(R.drawable.d, "chapter", R.drawable.d, 3));
		lists.add(map2);
		Map<String, ObjMap> map31 = new HashMap<String, ObjMap>();
		map31.put("type", new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(map31);
		Map<String, ObjMap> map32 = new HashMap<String, ObjMap>();
		map32.put("type", new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(map32);
		Map<String, ObjMap> map33 = new HashMap<String, ObjMap>();
		map33.put("type", new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(map33);
		Map<String, ObjMap> map34 = new HashMap<String, ObjMap>();
		map34.put("type", new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(map34);
		Map<String, ObjMap> map35 = new HashMap<String, ObjMap>();
		map35.put("type", new ObjMap(R.drawable.z, "page", R.drawable.d, 3));
		lists.add(map35);
		
		
		Map<String, ObjMap> map3 = new HashMap<String, ObjMap>();
		map3.put("type", new ObjMap(R.drawable.e, "chapter", R.drawable.e, 4));
		lists.add(map3);
		Map<String, ObjMap> map41 = new HashMap<String, ObjMap>();
		map41.put("type", new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(map41);
		Map<String, ObjMap> map42 = new HashMap<String, ObjMap>();
		map42.put("type", new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(map42);
		Map<String, ObjMap> map43 = new HashMap<String, ObjMap>();
		map43.put("type", new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(map43);
		Map<String, ObjMap> map44 = new HashMap<String, ObjMap>();
		map44.put("type", new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(map44);
		Map<String, ObjMap> map45 = new HashMap<String, ObjMap>();
		map45.put("type", new ObjMap(R.drawable.z, "page", R.drawable.e, 4));
		lists.add(map45);
		
		
		Map<String, ObjMap> map4 = new HashMap<String, ObjMap>();
		map4.put("type", new ObjMap(R.drawable.f, "chapter", R.drawable.f, 5));
		lists.add(map4);
		Map<String, ObjMap> map51 = new HashMap<String, ObjMap>();
		map51.put("type", new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(map51);
		Map<String, ObjMap> map52 = new HashMap<String, ObjMap>();
		map52.put("type", new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(map52);
		Map<String, ObjMap> map53 = new HashMap<String, ObjMap>();
		map53.put("type", new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(map53);
		Map<String, ObjMap> map54 = new HashMap<String, ObjMap>();
		map54.put("type", new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(map54);
		Map<String, ObjMap> map55 = new HashMap<String, ObjMap>();
		map55.put("type", new ObjMap(R.drawable.z, "page", R.drawable.f, 5));
		lists.add(map55);
		
		
		Map<String, ObjMap> map5 = new HashMap<String, ObjMap>();
		map5.put("type", new ObjMap(R.drawable.g, "chapter", R.drawable.g, 6));
		lists.add(map5);
		Map<String, ObjMap> map61 = new HashMap<String, ObjMap>();
		map61.put("type", new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(map61);
		Map<String, ObjMap> map62 = new HashMap<String, ObjMap>();
		map62.put("type", new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(map62);
		Map<String, ObjMap> map63 = new HashMap<String, ObjMap>();
		map63.put("type", new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(map63);
		Map<String, ObjMap> map64 = new HashMap<String, ObjMap>();
		map64.put("type", new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(map64);
		Map<String, ObjMap> map65 = new HashMap<String, ObjMap>();
		map65.put("type", new ObjMap(R.drawable.z, "page", R.drawable.g, 6));
		lists.add(map65);
		
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

			i.setImageResource(lists.get(position).get("type").getDrawable());
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
		ObjMap oMap = lists.get(position).get("type");
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