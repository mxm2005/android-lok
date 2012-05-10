package com.sk.stopcall.util;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.telephony.TelephonyManager;

public class PhoneReceiver extends BroadcastReceiver {
    private static ITelephony iTelephony;
    private static boolean flag = false;
    private static String phoneNumber = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        TelephonyManager telephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Method getITelephonyMethod = null;
        try {
            getITelephonyMethod = TelephonyManager.class.getDeclaredMethod("getITelephony", (Class[]) null);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        getITelephonyMethod.setAccessible(true);
        
        try {
            iTelephony = (ITelephony) getITelephonyMethod.invoke(telephonyMgr, (Object[]) null);
        } catch (Exception e) {
            // TODO: handle exception\
            e.printStackTrace();
        }

//        System.out.println("action" + intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            // 如果是去电（拨出）
            System.out.println("拨出");
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            flag = true;
            System.out.println("flag: " + flag);
        } else if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            System.out.println("+++" + "我进到这里来了。。。。");
            System.out.println("=========" + phoneNumber);
            if ("13342978945".equals(phoneNumber) && flag) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    iTelephony.endCall();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                System.out.println("+++" + "我进到这里来了2222。。。。");
                ComponentName componentName = new ComponentName("com.sk.stopcall", "com.sk.stopcall.StopcallActivity");
                Intent intent2 = new Intent();
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.setComponent(componentName);
                context.startActivity(intent2);
                flag = false;
                System.out.println("flag: " + flag);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Delete.delete(context, phoneNumber);
                System.out.println("结束");
            }
        }
    }
    
}
