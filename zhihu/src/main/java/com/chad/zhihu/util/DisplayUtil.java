package com.chad.zhihu.util;

import android.content.Context;

public class DisplayUtil {

    private static final String TAG = DisplayUtil.class.getSimpleName();

    public static int dp2px(Context context, int dip) {
        LogUtil.d(TAG, "dp2px : context = " + context + " , dip = " + dip);
        if (context == null) {
            return dip;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        LogUtil.d(TAG, "dp2px : scale = " + scale);
        return (int) (dip * scale + 0.5f);
    }
}
