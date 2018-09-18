package com.chad.weibo.helper;

import android.app.Activity;
import android.content.Intent;

import com.chad.weibo.ui.activity.AuthActivity;
import com.chad.weibo.ui.activity.MainActivity;

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
}
