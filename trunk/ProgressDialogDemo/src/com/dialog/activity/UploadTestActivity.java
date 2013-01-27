package com.dialog.activity;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UploadTestActivity extends Activity {
	
	public static final String TAG="UploadActivity";
    public static final int FTP_BROWSER_FILE=0x1001;
    FTPClient mFtpClient=null;
    TextView uploadTxt;
    private ProgressBar pb;
    Button uploadButton;
    int i=1;
    
    FTPDataTransferListener listener = new FTPDataTransferListener() {
    	 long mTotalSize=100;
		@Override
		public void transferred(long length) {   //显示已经传输的字节数
			 Log.d(TAG, "transferred.length:"+length);
	            int progress=(int) (length*100.0/mTotalSize);
	            Message msg;
	            msg=Message.obtain();
	            msg.what=1;
	            msg.obj=progress;
	            msg.arg1=i;
	            UploadTestActivity.this.mHandler.sendMessage(msg);
		}
		
		@Override
		public void transferred(int length) {    
			 
		}
		
		@Override
		public void started(long totalSize) {   //文件开始上传或下载时触发
			Log.d(TAG, "transferred.mTotalSize:"+totalSize);
            this.mTotalSize=totalSize;
            Message msg;

            msg=Message.obtain();
            msg.what=2;
            msg.obj=(int) totalSize;
            UploadTestActivity.this.mHandler.sendMessage(msg);
			
		}
		
		@Override
		public void started() {   
			System.out.println("started");
			
		}
		
		@Override
		public void failed() {   //传输失败时触发
			Log.d(TAG, "failed");
            Message msg;
            msg=Message.obtain();
            msg.what=5;
            mHandler.sendMessage(msg);
			
		}
		
		@Override
		public void completed() {   //文件传输完成时，触发
			Log.d(TAG, "completed");
            Message msg;
            msg=Message.obtain();
            msg.what=3;
            mHandler.sendMessage(msg);
			
		}
		
		@Override
		public void aborted() {  //传输放弃时触发
			Log.d(TAG, "aborted");
            Message msg;
            msg=Message.obtain();
            msg.what=4;
            mHandler.sendMessage(msg);
			
		}
	};
    
    Handler mHandler=new Handler() {
    	@Override
        public void handleMessage(Message msg) {
            int what=msg.what;
            Integer progress=(Integer) msg.obj;
            Integer a = (Integer) msg.arg1;
            switch (what) {
                case 1:
                    Log.d(TAG, "setProgress(progress)"+progress);
                    pb.setProgress(progress);
                    String progressString= progress.toString();
                    String aString = a.toString();
                    uploadTxt.setText(progressString+"%"+a+"/10");
                    break;

                case 2:
                    Log.d(TAG, "setMax(progress)"+progress);
                    pb.setVisibility(ProgressBar.VISIBLE);
                    pb.setVisibility(View.VISIBLE);
                    pb.setMax(100);
                    break;

                case 3:
                    //pb.setVisibility(View.GONE);
                    break;

                case 4: //aborted
                    //pb.setVisibility(View.GONE);
                    break;

                case 5: //failed
                    //pb.setVisibility(View.GONE);
                    break;
            }
        }
    };
    
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id=view.getId();
            switch (id) {
                case R.id.button1:
                	new Thread(downloadRun).start();
                    break;
            }
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_test);
        
        uploadTxt=(TextView) findViewById(R.id.upload_txt);
        uploadButton=(Button)findViewById(R.id.button1);
        pb=(ProgressBar) findViewById(R.id.progress_bar);
        uploadButton.setOnClickListener(clickListener);
    }
    
    Runnable downloadRun = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			FTPClient myFtp = new FTPClient();
			try {
				myFtp.connect("192.168.0.103", 21);
				myFtp.login("M1", "1");
				for(i=1;i<=10;i++)
				{
					myFtp.upload(new File("mnt/sdcard/sdcard/test/"+i+".jpg"),listener);
				}
				myFtp.disconnect(true);
			} catch (FTPDataTransferException e) {
				
				e.printStackTrace();
			} catch (FTPAbortedException e) {
				
				e.printStackTrace();
			} catch (IllegalStateException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (FTPIllegalReplyException e) {
				
				e.printStackTrace();
			} catch (FTPException e) {
				
				e.printStackTrace();
			}
			
		}
	};
}
