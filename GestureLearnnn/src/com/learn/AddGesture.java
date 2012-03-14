package com.learn;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddGesture extends Activity {
    private GestureOverlayView gestureNew;
    private GestureLibrary libraryNew;
    private Gesture gesture;// 手写实例
    private EditText txtNum;
    private Button btnOk;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgesture);
        init();
    }

    /**
     * 处理监听，初始化组件
     */
    public void init() {
        gestureNew = (GestureOverlayView) findViewById(R.id.gestureAdd);
        txtNum = (EditText) findViewById(R.id.txtNum);
        btnOk = (Button) findViewById(R.id.btnOK);
        btnOk.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 返回按钮。
                finish();
            }
        });
        
        // 添加手势绘制区的监听，需要实现4个方法
        gestureNew.addOnGestureListener(new GestureOverlayView.OnGestureListener() {
                    // 开始绘制时调用
                    public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
                    }

                    // 以下方法是当手势完整形成的时候触发,主要处理在这个方法中实现
                    public void onGestureEnded(GestureOverlayView overlay,
                            MotionEvent event) {
                        Log.e("sc", "进入绘制完成");
                        gesture = overlay.getGesture();
                        // 如果没有输入
                        if (txtNum.getText().toString().equals("")) {
                            Toast.makeText(AddGesture.this, "请输入号码",
                                    Toast.LENGTH_LONG).show();
                            txtNum.setFocusable(true);
                            gestureNew.clear(true);

                        }
                        // 利用正则表达试判断输入
                        else if (!(txtNum.getText().toString()
                                .matches("[0-9]*"))) {
                            Toast.makeText(AddGesture.this, "输入的必须是数字",
                                    Toast.LENGTH_LONG).show();
                            txtNum.setFocusable(true);

                        } else {
                            // 读取文件
                            libraryNew = GestureLibraries
                                    .fromFile("/sdcard/gestures");
                            Log.e("sc", "读取文件完毕" + libraryNew.toString());
                            libraryNew.load();
                            // 给手势仓库添加当前的手势
                            libraryNew.addGesture(txtNum.getText().toString(),
                                    gesture);
                            if (libraryNew.save())// 保存
                            {
                                gestureNew.clear(true);// 清除笔画
                                Toast.makeText(AddGesture.this, "保存成功！",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(AddGesture.this, "保存失败",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    public void onGestureCancelled(GestureOverlayView overlay,
                            MotionEvent event) {
                        // TODO Auto-generated method stub

                    }

                    public void onGesture(GestureOverlayView overlay,
                            MotionEvent event) {
                        // TODO Auto-generated method stub

                    }
                });
    }

}
