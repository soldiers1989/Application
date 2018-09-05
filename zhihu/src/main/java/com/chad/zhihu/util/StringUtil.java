package com.chad.zhihu.util;

import android.content.Context;

public class StringUtil {

    public static String findStringById(Context context, int id) {
        if (context == null) {
            return null;
        }
        return context.getResources().getString(id);
    }
}
