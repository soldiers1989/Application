package com.chad.zhihu.hepler;

import android.app.Activity;
import android.content.Intent;

import com.chad.zhihu.R;
import com.chad.zhihu.app.Constant;
import com.chad.zhihu.ui.activity.CommentActivity;
import com.chad.zhihu.ui.activity.ThemeDetailsActivity;
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

    public static void startThemeDetailsActivity(Activity activity, int id, String title) {
        LogUtil.d(TAG, "startThemeDetailsActivity : activity = " + activity
                + " , id = " + id + " , title = " + title);
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ThemeDetailsActivity.class);
        intent.putExtra(Constant.EXTRA_ID, id);
        intent.putExtra(Constant.EXTRA_TITLE, title);
        activity.startActivity(intent);
    }

    public static void startDetailsActivity(Activity activity, ArrayList<Integer> storiesIds, int id) {
        LogUtil.d(TAG, "startDetailsActivity : activity = " + activity
                + " , storiesIds.size = " + storiesIds.size() + " , id = " + id);
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putIntegerArrayListExtra(Constant.EXTRA_ID_LIST, storiesIds);
        intent.putExtra(Constant.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    public static void startCommentActivity(Activity activity, int comments, int longComments,
                                            int shortComments, int id) {
        LogUtil.d(TAG, "startCommentsActivity : activity = " + activity
                + " , comments = " + comments + " , longComments = " + longComments
                + " , shortComments = " + shortComments + " , id = " + id);
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, CommentActivity.class);
        intent.putExtra(Constant.EXTRA_COMMENTS, comments);
        intent.putExtra(Constant.EXTRA_COMMENTS_LONG, longComments);
        intent.putExtra(Constant.EXTRA_COMMENTS_SHORT, shortComments);
        intent.putExtra(Constant.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    public static void share(Activity activity, String title, int id) {
        LogUtil.d(TAG, "share : activity = " + activity
                + " , title = " + title + " , id = " + id);
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.share_subject));
        intent.putExtra(Intent.EXTRA_TEXT, activity.getString(R.string.share_from) + title
                + " , " + Constant.URL_SHARE + id);
        activity.startActivity(Intent.createChooser(intent, title));
    }
}
