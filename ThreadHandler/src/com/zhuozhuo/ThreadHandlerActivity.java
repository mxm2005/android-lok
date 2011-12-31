package com.zhuozhuo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ThreadHandlerActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final int MSG_SUCCESS = 0;//
	private static final int MSG_FAILURE = 1;//
	
	private ImageView mImageView;
	private Button mButton;
	
	private Thread mThread;
	
	private Handler mHandler = new Handler() {
		public void handleMessage (Message msg) {//�˷�����ui�߳�����
			switch(msg.what) {
			case MSG_SUCCESS:
				mImageView.setImageBitmap((Bitmap) msg.obj);//imageview��ʾ�������ȡ����logo
				Toast.makeText(getApplication(), getApplication().getString(R.string.get_pic_success), Toast.LENGTH_LONG).show();
				break;

			case MSG_FAILURE:
				Toast.makeText(getApplication(), getApplication().getString(R.string.get_pic_failure), Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mImageView= (ImageView) findViewById(R.id.imageView);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mThread == null) {
					mThread = new Thread(runnable);
					mThread.start();
				}
				else {
					Toast.makeText(getApplication(), getApplication().getString(R.string.thread_started), Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    
    Runnable runnable = new Runnable() {
		
		@Override
		public void run() {//run()���µ��߳�������
			HttpClient hc = new DefaultHttpClient();
			HttpGet hg = new HttpGet("http://www.linuxidc.com/pic/logo.gif");//��ȡLinux�����logo
			final Bitmap bm;
			try {
				HttpResponse hr = hc.execute(hg);
				bm = BitmapFactory.decodeStream(hr.getEntity().getContent());
			} catch (Exception e) {
				mHandler.obtainMessage(MSG_FAILURE).sendToTarget();//��ȡͼƬʧ��
				return;
			}
			mHandler.obtainMessage(MSG_SUCCESS,bm).sendToTarget();//��ȡͼƬ�ɹ�����ui�̷߳���MSG_SUCCESS��Ϣ��bitmap����

//			mImageView.setImageBitmap(bm); //���������ڷ�ui�̲߳���uiԪ��

//			mImageView.post(new Runnable() {//����һ�ָ����ķ�����Ϣ��ui�̵߳ķ�����
//				
//				@Override
//				public void run() {//run()��������ui�߳�ִ��
//					mImageView.setImageBitmap(bm);
//				}
//			});
		}
	};
	
}