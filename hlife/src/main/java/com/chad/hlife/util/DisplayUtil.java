package com.chad.hlife.util;

import android.content.Context;

public class DisplayUtil {

    public static int dp2Px(Context context, int dip) {
        if (context == null) {
            return dip;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
}
