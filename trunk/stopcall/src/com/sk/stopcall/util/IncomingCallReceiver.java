package com.sk.stopcall.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

public class IncomingCallReceiver extends BroadcastReceiver {

    private static final String TAG = "PersonServiceTest";

    private AudioManager mAudioManager;

    private ITelephony iTelephony;

    @Override
    public void onReceive(Context context, Intent intent) {

        mAudioManager = (AudioManager) context
                .getSystemService(Context.AUDIO_SERVICE);
        TelephonyManager telephonyMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
            Method getITelephonyMethod = null;
            try {
                getITelephonyMethod = TelephonyManager.class.getDeclaredMethod("getITelephony", (Class[]) null);
            } catch (SecurityException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (NoSuchMethodException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            getITelephonyMethod.setAccessible(true);
            try {
                iTelephony = (ITelephony) getITelephonyMethod.invoke(telephonyMgr, (Object[]) null);
            } catch (IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        String action = intent.getAction();
        Log.d(TAG, "action:" + action);

        if ("android.intent.action.PHONE_STATE".equals(action)) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
                
//                if ("13342978945".equals(number) || "13714720881".equals(number)) {
//                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//                    mAudioManager.setVibrateSetting(
//                            AudioManager.VIBRATE_TYPE_RINGER,
//                            AudioManager.VIBRATE_SETTING_OFF);
//                    try {
//                        iTelephony.endCall();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//                    mAudioManager.setVibrateSetting(
//                            AudioManager.VIBRATE_TYPE_RINGER,
//                            AudioManager.VIBRATE_SETTING_ON);
//                    abortBroadcast();
//                    
//                }
                
            }
        }
    }

}
