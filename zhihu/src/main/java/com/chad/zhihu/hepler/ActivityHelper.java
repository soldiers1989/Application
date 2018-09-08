package com.chad.zhihu.hepler;

import android.app.Activity;
import android.content.Intent;

import com.chad.zhihu.app.Constant;
import com.chad.zhihu.ui.activity.DetailsActivity;
import com.chad.zhihu.ui.activity.MainActivity;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;

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

    public static void startDetailsActivity(Activity activity, ArrayList<Integer> storiesIds, int id) {
        LogUtil.d(TAG, "startDetailsActivity : activity = " + activity
                        +  " , storiesIds.size = " + storiesIds.size() + " , id = " + id);
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putIntegerArrayListExtra(Constant.EXTRA_ID_LIST, storiesIds);
        intent.putExtra(Constant.EXTRA_ID, id);
        activity.startActivity(intent);
    }
}
