package com.chapter8.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.R;
import android.content.Context;

public class CopyDatabase {
    private Context context;

    public CopyDatabase(Context context) {
        this.context = context;
    }

    /*public void copyDatabase() {
        try {
            // 获得expot.db文件的绝对路径
            String databaseFilename = context.getFileStreamPath("export.db").getPath();
            if (!(new File(databaseFilename)).exists()) {
                InputStream is = context.getResources().openRawResource(R.raw.export);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
