package com.chad.weibo.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
}
