package com.chad.zhihu.util;

import android.content.Context;

public class DisplayUtil {

    private static final String TAG = DisplayUtil.class.getSimpleName();

    public static int dpToPx(Context context, int dip) {
        LogUtil.d(TAG, "dpToPx : context = " + context + " , dip = " + dip);
        if (context == null) {
            return dip;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        LogUtil.d(TAG, "dpToPx : scale = " + scale);
        return (int) (dip * scale + 0.5f);
    }
}
