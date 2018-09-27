package com.chad.hlife.util;

import android.content.Context;

import com.chad.hlife.R;

import java.util.Calendar;

public class DateUtil {

    public static String getYearMonthDay(Context context) {
        if (context == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + context.getString(R.string.year) + month + context.getString(R.string.month)
                + day + context.getString(R.string.day);
    }

    public static String getMonthDay() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return month + "/" + day;
    }
}
