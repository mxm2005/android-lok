package com.learn;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GestureLearnActivity extends Activity implements OnGesturePerformedListener, OnClickListener {
    private Button btnAdd;
    private Button btnSelect;
    private GestureOverlayView gesture;
    private GestureLibrary library;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        // 获得布局中的组件
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(this);
        gesture = (GestureOverlayView) findViewById(R.id.gestures);
        // 手势文件的加载路径
        String path = "/sdcard/gestures";
        // 加载手势文件
        library = GestureLibraries.fromFile(path);
        if (library.load()) {
            gesture.addOnGesturePerformedListener(this);
        } else {
            Toast.makeText(GestureLearnActivity.this, "无手势文件", Toast.LENGTH_LONG).show();
            File file = new File(path);
            try {
                // 创建手势文件
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 这个接口里处理匹配的手势
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = library.recognize(gesture);
        if (predictions.size() > 0) {
            // 获得识别率
            Prediction predict = predictions.get(0);
            // 如果识别率大于1，则说明找到匹配手势
            if (predict.score > 1.0) {
                // 调用打电话activity
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + predict.name));
                // 不打电话，只进入打电话界面
                // Intent phone=new
                // Intent("com.android.phone.action.TOUCH_DIALER");
                startActivity(intent);
            }
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnAdd:// 处理按下添加手势按钮
            Intent intent = new Intent(getApplicationContext(), AddGesture.class);
            startActivity(intent);
            // 切换activity动画
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
            break;
        case R.id.btnSelect:// 处理按下查看所有按钮
            Intent select = new Intent(getApplicationContext(), ListGestures.class);
            startActivity(select);
        default:
            break;
        }
    }

    private static Boolean isExit = false;// 标志是否按下返回键

    // 自定义计时任务，这里在2秒之后如果没有按下按钮，则将标志回复成原值。
    class task extends TimerTask {
        @Override
        public void run() {
            isExit = false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                task tt = new task();
                Timer tExit = new Timer();
                tExit.schedule(tt, 3000);
            } else {
                finish();
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                System.exit(0);
            }
        }
        return false;
    }

    @Override
    protected void onRestart() {
        init();
        super.onRestart();
    }

}