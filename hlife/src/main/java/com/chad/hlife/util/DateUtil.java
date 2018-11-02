package com.chad.hlife.util;

import android.content.Context;
import android.text.TextUtils;

import com.chad.hlife.R;
import java.text.SimpleDateFormat;
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
        String monthStr = Integer.toString(month);
        if (month < 10) {
            monthStr = "0" + monthStr;
        }
        String dayStr = Integer.toString(day);
        if (day < 10) {
            dayStr = "0" + dayStr;
        }
        return monthStr + dayStr;
    }

    public static String formatData(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        String year = date.substring(0, date.length() - 4);
        String month = date.substring(date.length() - 4, date.length() - 2);
        String day = date.substring(date.length() - 2, date.length());
        return year + "-" + month + "-" + day;
    }

    public static String formatDate(Context context, String date) {
        if (context == null || TextUtils.isEmpty(date)) {
            return null;
        }
        String month = date.substring(4, 6);
        String day = date.substring(6, date.length());
        String monthUnit = context.getString(R.string.month);
        String dayUnit = context.getString(R.string.day);
        return month + monthUnit + day + dayUnit;
    }

    public static String formatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        return format.format(time);
    }
}
