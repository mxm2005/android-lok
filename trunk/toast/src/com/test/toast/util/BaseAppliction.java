package com.test.toast.util;

import com.test.toast.R;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class BaseAppliction extends Application {
    WindowManager mWM;
    WindowManager.LayoutParams mWMParams;

    @Override
    public void onCreate() {
        mWM = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        final View win = LayoutInflater.from(this).inflate(
                R.layout.ctrl_window, null);

        win.setOnTouchListener(new OnTouchListener() {
            float lastX, lastY;

            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();

                float x = event.getX();
                float y = event.getY();
                if (action == MotionEvent.ACTION_DOWN) {
                    lastX = x;
                    lastY = y;
                } else if (action == MotionEvent.ACTION_MOVE) {
                    mWMParams.x += (int) (x - lastX);
                    mWMParams.y += (int) (y - lastY);
                    mWM.updateViewLayout(win, mWMParams);
                }
                return true;
            }
        });

        WindowManager wm = mWM;
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        mWMParams = wmParams;
        wmParams.type = 2002; // type是关键，这里的2002表示系统级窗口，你也可以试试2003。
        wmParams.format = 1;
        wmParams.flags = 40;

        wmParams.width = 300;
        wmParams.height = 200;

        wm.addView(win, wmParams);
    }
}