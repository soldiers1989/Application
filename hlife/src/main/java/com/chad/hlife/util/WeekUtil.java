package com.chad.hlife.util;

import android.content.Context;
import android.text.TextUtils;

import com.chad.hlife.R;

import java.util.Calendar;

public class WeekUtil {

    public static String formatWeek(Context context, String date) {
        if (context == null || TextUtils.isEmpty(date)) {
            return null;
        }
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String format = null;
        switch (week) {
            case 1:
                format = context.getString(R.string.sunday);
                break;
            case 2:
                format = context.getString(R.string.monday);
                break;
            case 3:
                format = context.getString(R.string.tuesday);
                break;
            case 4:
                format = context.getString(R.string.wednesday);
                break;
            case 5:
                format = context.getString(R.string.thursday);
                break;
            case 6:
                format = context.getString(R.string.friday);
                break;
            case 7:
                format = context.getString(R.string.saturday);
                break;
            default:
                break;
        }
        return format;
    }
}
