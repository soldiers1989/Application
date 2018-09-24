package com.chad.weibo.util;

import android.content.Context;
import android.text.TextUtils;

import com.chad.weibo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final int TYPE_TIME_MINUTE = 1000 * 60;
    public static final int TYPE_TIME_HOUR = 1000 * 60 * 60;

    public static String formatDate(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    public static String formatTime(Context context, String time) {
        Date date = new Date(time);
        int timeDifference = getTimeDifference(date, new Date(), TYPE_TIME_MINUTE);
        if (timeDifference == 0) {
            return context.getString(R.string.just);
        } else if (timeDifference < 60) {
            return timeDifference + context.getString(R.string.minutes_ago);
        }

        timeDifference = getTimeDifference(date, new Date(), TYPE_TIME_HOUR);
        if (timeDifference < 24) {
            return timeDifference + context.getString(R.string.hours_ago);
        } else if (timeDifference < 48) {
            return context.getString(R.string.yesterday);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public static int getTimeDifference(Date start, Date end, int type) {
        long startTime = getTime(start);
        long endTime = getTime(end);
        return (int) ((endTime - startTime) / type);
    }

    public static long getTime(Date date) {
        return date.getTime();
    }
}
