package com.chad.zhihu.util;

import android.content.Context;

public class ColorUtil {

    private static final String TAG = ColorUtil.class.getSimpleName();

    public static int findRgbById(Context context, int id) {
        LogUtil.d(TAG, "findRgbById : id = " + id + " , context = " + context);
        if (context == null) {
            return 0;
        }
        return context.getResources().getColor(id);
    }
}
