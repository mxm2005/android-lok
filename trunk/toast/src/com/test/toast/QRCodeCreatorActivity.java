package com.test.toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * Android中二维码编码及图像生成
 * 
 */
public class QRCodeCreatorActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String content = "http://weibo.com/lok206/";
        final int desiredWidth = 120;
        final int desiredHeight = 120;
        final String imageFileName = "orgcent.com.png";
        FileOutputStream fos = null;
        Bitmap bitmap = null;
        try {// 生成二维码图像
            bitmap = encodeAsBitmap(content, BarcodeFormat.QR_CODE,
                    desiredWidth, desiredHeight);
            if (null != bitmap) {// 将二维码图像保存到文件
                File file = new File(Environment.getExternalStorageDirectory(),
                        imageFileName);
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }

        // 显示QRCode
        if (null != bitmap) {
            ImageView iv = new ImageView(this);
            iv.setImageBitmap(bitmap);
            iv.setScaleType(ScaleType.FIT_CENTER);
            setContentView(iv, new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }
    
    /**
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public void saveFile(Bitmap bm, String fileName) throws IOException {   
        File dirFile = new File(Environment.getExternalStorageDirectory().toString());   
        if(!dirFile.exists()){   
            dirFile.mkdir();   
        }   
        File myCaptureFile = new File(Environment.getExternalStorageDirectory() + "/" + fileName);   
        FileOutputStream fos = new FileOutputStream(myCaptureFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);   
        bm.compress(Bitmap.CompressFormat.PNG, 80, bos);   
        bos.flush();   
        bos.close();   
    }   

    static Bitmap encodeAsBitmap(String contents, BarcodeFormat format,
            int desiredWidth, int desiredHeight) throws WriterException {
        final int WHITE = 0xFFFFFFFF; // 可以指定其他颜色，让二维码变成彩色效果
        final int BLACK = 0xFF000000;

        HashMap<EncodeHintType, String> hints = null;
        String encoding = guessAppropriateEncoding(contents);
        if (encoding != null) {
            hints = new HashMap<EncodeHintType, String>(2);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = writer.encode(contents, format, desiredWidth,
                desiredHeight, hints);
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        // All are 0, or black, by default
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}