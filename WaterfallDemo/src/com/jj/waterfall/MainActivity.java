package com.jj.waterfall;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		LazyScrollView.OnScrollListener {
	/** Called when the activity is first created. */
	private LinearLayout layout01, layout02, layout03;// 3��

	private List<String> image_filenames; // ͼƬ����

	private ImageDownLoadAsyncTask asyncTask;

	private AssetManager assetManager;

	private int Image_width;// ͼƬ��ʾ�Ŀ��

	private int x, y;// �У���

	private int current_page = 0;// ҳ��
	private int count = 15;// ÿҳ��ʾ�ĸ���

	private LazyScrollView lazyScrollView;// �Զ���scrollview

	private LinearLayout progressbar;// ������

	private TextView loadtext;// �ײ�����view

	private String tag = "jj";

	/***
	 * init view
	 */
	public void InitView() {
		setContentView(R.layout.main);
		lazyScrollView = (LazyScrollView) findViewById(R.id.lazyscrollview);
		progressbar = (LinearLayout) findViewById(R.id.progressbar);
		loadtext = (TextView) findViewById(R.id.loadtext);
		lazyScrollView.getView();
		lazyScrollView.setOnScrollListener(this);
		layout01 = (LinearLayout) findViewById(R.id.layout01);
		layout02 = (LinearLayout) findViewById(R.id.layout02);
		layout03 = (LinearLayout) findViewById(R.id.layout03);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getTitle() + "-jjhappyforever...");
		InitView();

		assetManager = this.getAssets();
		// ��ȡ��ʾͼƬ���
		Image_width = (getWindowManager().getDefaultDisplay().getWidth() - 4) / 3;
		try {
			image_filenames = Arrays.asList(assetManager.list("images"));// ��ȡͼƬ����
		} catch (IOException e) {
			e.printStackTrace();
		}

		addImage(current_page, count);

	}

	/***
	 * ���imageview ��layout
	 * 
	 * @param imagePath
	 *            ͼƬname
	 * @param j
	 *            ��
	 * @param i
	 *            ��
	 */
	public void addBitMapToImage(String imagePath, int j, int i) {
		ImageView imageView = getImageview();
		asyncTask = new ImageDownLoadAsyncTask(this, imagePath, imageView,
				Image_width);
		asyncTask.setProgressbar(progressbar);
		asyncTask.setLoadtext(loadtext);
		asyncTask.execute();

		imageView.setTag(i);
		if (j == 0) {
			layout01.addView(imageView);
		} else if (j == 1) {
			layout02.addView(imageView);
		} else if (j == 2) {
			layout03.addView(imageView);
		}

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,
						"�������" + v.getTag() + "��Item", Toast.LENGTH_SHORT)
						.show();

			}
		});
	}

	/***
	 * 
	 * ������ʾimageview setScaleType ��⣺ CENTER /center
	 * ��ͼƬ��ԭ��size������ʾ����ͼƬ��/����View�ĳ�/����� ȡͼƬ�ľ��в�����ʾ CENTER_CROP / centerCrop
	 * ����������ͼƬ��size������ʾ��ʹ��ͼƬ�� (��)���ڻ����View�ĳ�(��) CENTER_INSIDE / centerInside
	 * ��ͼƬ����������������ʾ��ͨ����������С ��ԭ����sizeʹ��ͼƬ��/����ڻ�С��View�ĳ�/�� FIT_CENTER / fitCenter
	 * ��ͼƬ����������/��С��View�Ŀ�ȣ�������ʾ FIT_END / fitEnd ��
	 * ͼƬ����������/��С��View�Ŀ�ȣ���ʾ��View���²���λ�� FIT_START / fitStart ��
	 * ͼƬ����������/��С��View�Ŀ�ȣ���ʾ��View���ϲ���λ�� FIT_XY / fitXY ��ͼƬ �������� ����/��С��View�Ĵ�С��ʾ
	 * MATRIX / matrix �þ���������
	 * 
	 * @return
	 */
	public ImageView getImageview() {
		// ������ʾͼƬ�Ķ���
		ImageView imageView = new ImageView(this);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		imageView.setLayoutParams(layoutParams);
		imageView.setPadding(2, 0, 2, 2);// ���ü��
		return imageView;
	}

	@Override
	public void onBottom() {
		addImage(++current_page, count);
	}

	/***
	 * ���ظ���
	 * 
	 * @param current_page
	 *            ��ǰҳ��
	 * @param count
	 *            ÿҳ��ʾ����
	 */
	private void addImage(int current_page, int count) {
		for (int x = current_page * count; x < (current_page + 1) * count
				&& x < image_filenames.size(); x++) {
			addBitMapToImage(image_filenames.get(x), y, x);
			y++;
			if (y >= 3)
				y = 0;
		}

	}

	@Override
	public void onTop() {
		Log.d(tag, "top");
	}

	@Override
	public void onScroll() {
		Log.d(tag, "scroll");
	}
}