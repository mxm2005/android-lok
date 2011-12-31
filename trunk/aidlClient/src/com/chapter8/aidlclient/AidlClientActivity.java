package com.chapter8.aidlclient;

import com.chapter8.aidl.IAIDLServerService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AidlClientActivity extends Activity {

    private TextView mTextView;
    private Button mButton;

//    private IAIDLServerService mIaidlServerService = null;
//
//    private ServiceConnection mConnection = new ServiceConnection() {
//
//        public void onServiceDisconnected(ComponentName name) {
//            mIaidlServerService = null;
//        }
//
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mIaidlServerService = IAIDLServerService.Stub.asInterface(service);
//            // aidl通信
//            try {
//                String mText = "Say hello: " + mIaidlServerService.sayHello()
//                        + "/n";
//                mText += "书名: " + mIaidlServerService.getBook().getBookName()
//                        + "/n";
//                mText += "价格: " + mIaidlServerService.getBook().getBookPrice();
//                mTextView.setText(mText);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    };
    
    private IAIDLServerService mIaidlServerService = null;
    private ServiceConnection mConnection = new ServiceConnection() {
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            mIaidlServerService = null;
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            mIaidlServerService = IAIDLServerService.Stub.asInterface(service);
            try {
                String mText = "Say hello: " + mIaidlServerService.sayHello() + "\n";
                mText += "书名：" + mIaidlServerService.getBook().getBookName() + "\n";
                mText += "价格: " + mIaidlServerService.getBook().getBookPrice();
                mTextView.setText(mText);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 初始化控件
        mTextView = (TextView) findViewById(R.id.textview);
        mButton = (Button) findViewById(R.id.button);
        // 增加事件响应
        mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // bindService
                Intent service = new Intent("com.chapter8.aidl.IAIDLServerService");
//                bindService(service, mConnection, BIND_AUTO_CREATE);
                bindService(service, mConnection, BIND_AUTO_CREATE);
            }

        });
    }

}