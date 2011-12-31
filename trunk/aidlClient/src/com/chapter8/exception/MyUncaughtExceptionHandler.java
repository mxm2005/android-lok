package com.chapter8.exception;

import android.util.Log;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler a;

    public MyUncaughtExceptionHandler() {
        this.a = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.i("huilurry", "ppppppppppppp=" + ex.getMessage());
        // 是否抛出异常
        if(a!=null) a.uncaughtException(thread, ex);
    }

}
