package com.chad.weibo.util;

import android.content.Context;

public class DisplayUtil {

    public static int dp2Px(Context context, int dip) {
        if (context == null) {
            return dip;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static float dip2px(Context context, float dp) {
        if (context == null) {
            return dp;
        }
        float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale;
    }
}
