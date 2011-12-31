package com.sk;

import java.text.Collator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.View;
import android.view.WindowManager;

public class HomeActivity extends Activity {
	
	static final String INTENAL_ACTION_2 = "com.testBroadcastReceiver.Internal_2";
	
	Set<String> sets = new HashSet<String>();
	
	private static class Loc implements Comparable {
        static Collator sCollator = Collator.getInstance();

        String label;
        Locale locale;

        public Loc(String label, Locale locale) {
            this.label = label;
            this.locale = locale;
        }

        @Override
        public String toString() {
            return this.label;
        }

        public int compareTo(Object o) {
            return sCollator.compare(this.label, ((Loc) o).label);
        }
    }
	
	boolean flag = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        offLine();
//        setBrightness(0.1f);
        
//        stopAutoBrightness(this);
//        setBrightness(this, 155);
//        saveBrightness(getContentResolver(), 155);
        
        
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (flag){
//					offLine(flag);
//					flag = false;
//				} else {
//					offLine(flag);
//					flag = true;
//				}
//				
//			}
//		});
        
//        Locale aa = Locale.getDefault();
//        System.out.println(aa.getDisplayLanguage().toString());
//        System.out.println(aa.getDisplayCountry(aa));
//        System.out.println(aa.getDisplayName());
//        System.out.println();
//        for (Locale fd : aa.getAvailableLocales()){
//        	System.out.println(fd.getDisplayLanguage() + "(" + fd.getDisplayCountry() + ")");
//        	sets.add(fd.getDisplayLanguage());
//        }
//        
//        for (Iterator iterator = sets.iterator(); iterator.hasNext();){
//        	System.out.println(iterator.next().toString());
//        }
//        System.out.println("数量" + sets.size());
        
//        System.out.println(aa.getAvailableLocales().length);
        
//        Configuration conf = getResources().getConfiguration();
//        String locale = conf.locale.getDisplayName(conf.locale);
//        if (locale != null && locale.length() > 1) {
//            locale = Character.toUpperCase(locale.charAt(0)) + locale.substring(1);
//            System.out.println(locale);
//        }
    }
    
    protected void offLine(boolean setAirPlane) {
    	Settings.System.putInt(getContentResolver(),
    	Settings.System.AIRPLANE_MODE_ON, setAirPlane ? 1 : 0);
    	Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    	intent.putExtra("TestCode", "ellic");
    	sendBroadcast(intent);
    }
    
	// 飞行模式
	protected void offLine(/*boolean setAirPlane*/) {
		
		// Change the system setting
//        Settings.System.putInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 1);
//        
//        // Post the intent
//        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        intent.putExtra("state", true);
//        sendBroadcast(intent);
        
        Settings.System.putInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 1);  
        Intent localIntent1 = new Intent("android.intent.action.AIRPLANE_MODE").putExtra("state", true);  
        sendBroadcast(localIntent1);  
	    
		//飞行模式
//	    Settings.System.putInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 1);  
//	     Intent localIntent1 = new Intent("android.intent.action.AIRPLANE_MODE").putExtra("state", true);  
//	     sendBroadcast(localIntent1);  
		
		
		//正常模式 
//	    Settings.System.putInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);  
//        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);  
//        intent.putExtra("state", false);  
//        sendBroadcast(intent);  
	}
	
	protected void setBrightness(float f){
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.screenBrightness = f;//1.0f;
	}
	
	public void setBrightness2(float f){
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.screenBrightness = f;   
		ProgressDialog pd=new ProgressDialog(this);
		getWindow().setAttributes(lp);
	}
	
	/**
	 * 判断是否开启了自动亮度调节
	 */
	public static boolean isAutoBrightness(ContentResolver aContentResolver) {
	    boolean automicBrightness = false;
	    try {
			automicBrightness = Settings.System.getInt(aContentResolver,
					Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
	    } catch (SettingNotFoundException e) {
	        e.printStackTrace();
	    }
	    return automicBrightness;
	}
	
	/**
	 * 获取屏幕的亮度
	 */
	public static int getScreenBrightness(Activity activity) {
	    int nowBrightnessValue = 0;
	    ContentResolver resolver = activity.getContentResolver();
	    try {
	        nowBrightnessValue = android.provider.Settings.System.getInt(
	                resolver, Settings.System.SCREEN_BRIGHTNESS);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return nowBrightnessValue;
	}
	
	/**
	 * 设置亮度
	 */
	public static void setBrightness(Activity activity, int brightness) {
	    // Settings.System.putInt(activity.getContentResolver(),
	    // Settings.System.SCREEN_BRIGHTNESS_MODE,
	    // Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
	    lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
	    activity.getWindow().setAttributes(lp);
	}
	
	/**
	 * 停止自动亮度调节
	 */
	public static void stopAutoBrightness(Activity activity) {
	    Settings.System.putInt(activity.getContentResolver(),
	            Settings.System.SCREEN_BRIGHTNESS_MODE,
	            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	}
	
	/**
	 * 开启亮度自动调节
	 *
	 * @param activity
	 */
	public static void startAutoBrightness(Activity activity) {
	    Settings.System.putInt(activity.getContentResolver(),
	            Settings.System.SCREEN_BRIGHTNESS_MODE,
	            Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	}
	
	/**
	 * 保存亮度设置状态
	 */
	public static void saveBrightness(ContentResolver resolver, int brightness) {
	    Uri uri = android.provider.Settings.System
	            .getUriFor("screen_brightness");
	    android.provider.Settings.System.putInt(resolver, "screen_brightness",
	            brightness);
	    // resolver.registerContentObserver(uri, true, myContentObserver);
	    resolver.notifyChange(uri, null);
	}
    
}