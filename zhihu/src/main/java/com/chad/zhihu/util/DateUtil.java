package com.chad.zhihu.util;

import android.content.Context;
import android.text.TextUtils;

import com.chad.zhihu.R;

public class DateUtil {

    public static String formatDate(Context context, String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String monthUnit = StringUtil.findStringById(context, R.string.unit_month);
        String dayUnit = StringUtil.findStringById(context, R.string.unit_day);
        return month + monthUnit + day + dayUnit;
    }
}
