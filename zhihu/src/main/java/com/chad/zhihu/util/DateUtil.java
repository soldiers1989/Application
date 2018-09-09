package com.chad.zhihu.util;

import android.content.Context;
import android.text.TextUtils;

import com.chad.zhihu.R;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String formatDate(Context context, String date) {
        if (context == null || TextUtils.isEmpty(date)) {
            return null;
        }
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String monthUnit = StringUtil.findStringById(context, R.string.unit_month);
        String dayUnit = StringUtil.findStringById(context, R.string.unit_day);
        return month + monthUnit + day + dayUnit;
    }

    public static String formatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        return format.format(time);
    }
}
