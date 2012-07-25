package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDemo {
    public static void main(String[] args) throws Exception {
        zipFile(new File("c:/a.log"), new File("c:/a.zip"));
    }

    public static void zipFile(File source, File target) throws IOException {
        byte[] buffer = new byte[1024];
        ZipOutputStream outputStream = new ZipOutputStream(
                new FileOutputStream(target));
        outputStream.putNextEntry(new ZipEntry(source.getName()));
        InputStream inputStream = new FileInputStream(source);
        for (int i = inputStream.read(buffer); i > 0; i = inputStream
                .read(buffer)) {
            outputStream.write(buffer, 0, i);
        }
        inputStream.close();
        outputStream.close();
    }
}
