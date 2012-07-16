package com.test.toast;

import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ITransientNotification;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.test.toast.util.CrashHandler;

public class ToastActivity extends Activity {
    Object obj;
    ITransientNotification notification;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //当前应用的代码执行目录
        upgradeRootPermission(getPackageCodePath());
        
        CrashHandler crashHandler = CrashHandler.getInstance();  
        crashHandler.init(this);  //传入参数必须为Activity，否则AlertDialog将不显示。
        // 创建错误
//        throw new NullPointerException();
        
        
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 先创建一个Toast对象
                Toast toast = Toast.makeText(ToastActivity.this, "永不消失的Toast",
                        Toast.LENGTH_SHORT);
                // 设置Toast信息提示框显示的位置（在屏幕顶部水平居中显示）
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                try {
                    // 从Toast对象中获得mTN变量
                    Field field = toast.getClass().getDeclaredField("mTN");
                    field.setAccessible(true);
                    obj = field.get(toast);
                    // TN对象中获得了show方法
                    Method method = obj.getClass().getDeclaredMethod("show", null);
                    // 调用show方法来显示Toast信息提示框
                    method.invoke(obj, null);
                } catch (Exception e) {
                }
            }
        });

        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    // 需要将前面代码中的obj变量变成类变量。这样在多个地方就都可以访问了
                    Method method = obj.getClass().getDeclaredMethod("hide", null);
                    method.invoke(obj, null);
                } catch (Exception e) {
                }
            }
        });
        
        findViewById(R.id.button3).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    Toast toast = Toast.makeText(ToastActivity.this, "永不消失的Toast", Toast.LENGTH_SHORT);
                    Field field = toast.getClass().getDeclaredField("mTN");
                    field.setAccessible(true);
                    notification = (ITransientNotification) field.get(toast);
                    // 显示Toast信息提示框
                    notification.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        findViewById(R.id.button4).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 关闭Toast信息提示框
                try {
                    notification.hide();
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        findViewById(R.id.button5).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ToastActivity.this, QRCodeCreatorActivity.class);
                startActivity(intent);
            }
        });
    }
    
    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     * 
     * @return 应用程序是/否获取Root权限
     */
    public static boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); // 切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }
    
}