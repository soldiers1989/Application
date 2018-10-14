package com.chad.hlife.util;

import android.content.Context;
import android.text.TextUtils;

import com.chad.hlife.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static String getCurrentTime(String time, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Long.toString(date.getTime()/1000);
    }

    public static String formatDate(Context context, String date) {
        if (context == null || TextUtils.isEmpty(date)) {
            return null;
        }
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String monthUnit = context.getString(R.string.month);
        String dayUnit = context.getString(R.string.day);
        return month + monthUnit + day + dayUnit;
    }

    public static String formatTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        return format.format(time);
    }
}
