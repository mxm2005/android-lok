package com.sk.stopcall;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sk.stopcall.util.IncomingCallReceiver;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.View.OnClickListener;


public class StopcallActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        findViewById(R.id.disableCallTransfer).setOnClickListener(new OnClickListener() {
//            
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent = new Intent(StopcallActivity.this, IncomingCallReceiver.class);
//                startActivity(intent);
//            }
//        });
//        String phoneNumber = android.os.SystemProperties.get(android.telephony.TelephonyProperties.PROPERTY_LINE1_NUMBER);
//        String operatorISOCountry = android.os.SystemProperties
//                .get(android.telephony.TelephonyProperties.PROPERTY_OPERATOR_ISO_COUNTRY);
//        String operatorName = android.os.SystemProperties
//                .get(android.telephony.TelephonyProperties.PROPERTY_OPERATOR_ALPHA);
//      On the emulator this will return: "15555218135", "us" and "Android".
//        mPhone = PhoneApp.getInstance().phone;
//        Intent intent = getIntent(); 
//        String action = intent.getAction(); 
//        String number = PhoneNumberUtils.getNumberFromIntent(intent, this);
    }
    

}