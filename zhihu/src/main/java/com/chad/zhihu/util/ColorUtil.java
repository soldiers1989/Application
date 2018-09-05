package com.chad.zhihu.util;

import android.content.Context;

public class ColorUtil {

    public static int findRgbById(Context context, int id) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getColor(id);
    }
}
