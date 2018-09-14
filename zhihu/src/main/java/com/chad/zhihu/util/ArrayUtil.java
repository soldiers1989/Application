package com.chad.zhihu.util;

import android.content.Context;

public class ArrayUtil {

    public static String[] findStringArrayById(Context context, int id) {
        if (context == null) {
            return null;
        }
        return context.getResources().getStringArray(id);
    }
}
