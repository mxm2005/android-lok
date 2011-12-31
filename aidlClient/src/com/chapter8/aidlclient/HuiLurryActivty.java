package com.chapter8.aidlclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Window;

import com.chapter8.exception.MyUncaughtExceptionHandler;

public class HuiLurryActivty extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.main);
        setProgressBarIndeterminateVisibility(true);
        String t = android.provider.Settings.System.getString(getContentResolver(), "android_id");
        Log.i("huilurry", "android_id=" + t);
        huilurry();
        throw new NullPointerException("is null");
    }

    HandlerThread localHandlerThread;
    Handler handler;

    private void huilurry() {
        localHandlerThread = new HandlerThread("huilurry");
        localHandlerThread.start();
        handler = new Handler(localHandlerThread.getLooper());
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    }
}