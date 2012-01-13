package com.kris.skin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kris.skin.utils.ZipUtil;
/**
 * 
 *
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since  2011-10-13  下午10:11:32
 * @version 1.0.0
 */
public class Re_Skin2Activity extends Activity implements OnClickListener{
	private Button	btnSet;
	private Button	btnImport;
	private LinearLayout layout;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnSet = (Button)findViewById(R.id.button1);
        btnSet.setOnClickListener(this);

        btnImport = (Button)findViewById(R.id.button2);
        btnImport.setOnClickListener(this);
        layout = (LinearLayout)findViewById(R.id.layout);
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Bitmap bitmap= BitmapFactory.decodeFile("/sdcard/Skin_kris/skin/google.png");
			
			 BitmapDrawable bd=new BitmapDrawable(bitmap);
			btnSet.setBackgroundDrawable(bd);
			
			layout.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeFile("/sdcard/Skin_kris/skin/bg/bg.png")));
			
			break;
		case R.id.button2:
			ZipUtil zipp = new ZipUtil(2049);
			System.out.println("begin do zip");
			zipp.unZip("/sdcard/skin.zip","/sdcard/Skin_kris");
			Toast.makeText(this, "导入成功", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}