package com.chad.zhihu.hepler;

import android.app.Activity;
import android.content.Intent;

import com.chad.zhihu.app.Constant;
import com.chad.zhihu.ui.activity.DetailActivity;
import com.chad.zhihu.ui.activity.MainActivity;
import com.chad.zhihu.util.LogUtil;

public class ActivityHelper {

    private static final String TAG = ActivityHelper.class.getSimpleName();

    public static void startMainActivity(Activity activity) {
        LogUtil.d(TAG, "startMainActivity : activity = " + activity);
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void startHomeDetailActivity(Activity activity, int id) {
        LogUtil.d(TAG, "startDetailActivity : activity = " + activity + " , id = "+ id);
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(Constant.EXTRA_ID, id);
        activity.startActivity(intent);
    }
}
