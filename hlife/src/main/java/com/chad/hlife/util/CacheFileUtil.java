package com.chad.hlife.util;

import android.content.Context;

import java.io.File;
import java.math.BigDecimal;

public class CacheFileUtil {

    public static String getCacheFileSize(Context context) {
        if (context == null) {
            return null;
        }
        long size = getFileSize(context.getCacheDir());
        return format(size);
    }

    public static long getFileSize(File file) {
        long size = 0;
        try {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    size = size + getFileSize(files[i]);
                } else {
                    size = size + files[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static String format(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    public static void clearCacheFile(Context context) {
        if (context == null) {
            return;
        }
        File cacheFile = context.getCacheDir();
        deleteDir(cacheFile);
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] file = dir.list();
            for (int i = 0; i < file.length; i++) {
                boolean success = deleteDir(new File(dir, file[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
