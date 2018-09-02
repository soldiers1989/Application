package com.chad.zhihu.util;

import android.content.Context;

public class StringUtil {

    private static final String TAG = StringUtil.class.getSimpleName();

    public static String findStringById(Context context, int id) {
        LogUtil.d(TAG, "findStringById : id = " + id + " , context = " + context);
        if (context == null) {
            return null;
        }
        return context.getResources().getString(id);
    }
}
