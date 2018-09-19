package com.chad.weibo.helper;

import android.app.Activity;
import android.content.Intent;

import com.chad.weibo.ui.activity.AuthActivity;
import com.chad.weibo.ui.activity.MainActivity;
import com.chad.weibo.ui.activity.UserActivity;

public class ActivityHelper {

    public static void startAuthActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, AuthActivity.class);
        activity.startActivity(intent);
    }

    public static void startMainActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void startUserActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, UserActivity.class);
        activity.startActivity(intent);
    }
}
