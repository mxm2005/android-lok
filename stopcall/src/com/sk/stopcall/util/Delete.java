package com.sk.stopcall.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

public class Delete {
    public static void delete(Context context, String number) {
        ContentResolver resolver = context.getContentResolver();  
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI,
                new String[] { "_id" }, "number=? and type=2",
                new String[] { number }, "_id desc limit 1");  
        if(cursor.moveToFirst()) {  
            int id = cursor.getInt(0);  
            resolver.delete(CallLog.Calls.CONTENT_URI, "_id=?", new String[] {id + ""});  
        }  
    }
}
