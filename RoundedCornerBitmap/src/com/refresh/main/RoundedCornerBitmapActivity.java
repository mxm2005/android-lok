package com.refresh.main;

import java.io.FileOutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;

public class RoundedCornerBitmapActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.valentines);
		// 左边显示原始图片
		ImageView image = (ImageView) findViewById(R.id.image);
		image.setImageBitmap(bitmap);

		Button write = (Button) findViewById(R.id.write);
		write.setOnClickListener(writeClick);
	}

	// 点击的事件响应
	OnClickListener writeClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.valentines);
			Bitmap b = getRoundedCornerBitmap(bitmap);
			// 图片显示在右边
			ImageView image = (ImageView) findViewById(R.id.round);
			image.setImageBitmap(b);
			// 在sdcard生成圆角图片
			try {
				FileOutputStream out = new FileOutputStream("/sdcard/test.png");
				// 将Bitmap 对象转换为图片文件，
				// 第一个参数是图片格式
				// 第二个参数是压缩质量，最大值为100，如果是png格式，则无损压缩
				// 第三个参数是写入的文件
				b.compress(Bitmap.CompressFormat.PNG, 90, out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	// 将图片圆角显示的函数,返回Bitmap
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		// 根据原来图片大小画一个矩形
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		// 圆角弧度参数,数值越大圆角越大,甚至可以画圆形
		final float roundPx = 12;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		// 画出一个圆角的矩形
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		// 取两层绘制交集,显示上层
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		// 显示图片
		canvas.drawBitmap(bitmap, rect, rect, paint);
		// 返回Bitmap对象
		return output;
	}
}