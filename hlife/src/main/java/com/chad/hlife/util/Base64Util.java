package com.chad.hlife.util;

import android.text.TextUtils;
import android.util.Base64;

public class Base64Util {

    public static String coding(String text) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return Base64.encodeToString(text.getBytes(), Base64.DEFAULT);
    }

    public static String deCoding(String base64) {
        if (TextUtils.isEmpty(base64)) {
            return null;
        }
        return new String(Base64.decode(base64.getBytes(),
                Base64.DEFAULT));
    }
}
