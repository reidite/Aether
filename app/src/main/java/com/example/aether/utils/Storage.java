package com.example.aether.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.Random;

public class Storage {
    private static final String DIRECTORY_NAME = "/Aether/data/com.example.aether";

    public static String getExternalRootDirectory() {
        return getDirectoryPath(DIRECTORY_NAME);
    }

    public static String getRandomName(int size) {
        char[] chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789"
                           .toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) sb.append(chars[random.nextInt(chars.length)]);
        return sb.toString();
    }

    private static String getDirectoryPath(String directory) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + directory;
            File dir = new File(path
                );
            if ( dir.exists() || dir.mkdirs() ) {
                return dir.getAbsolutePath();
            } else {
                throw new RuntimeException("Couldn't create external directory");
            }
        } else {
            throw new RuntimeException("External Storage is currently not available");
        }
    }

    public boolean isMounted() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
