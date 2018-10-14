package com.chad.hlife.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.chad.hlife.app.AppConstant;
import com.chad.hlife.ui.activity.MainActivity;
import com.chad.hlife.ui.juhe.activity.BooksDetailActivity;
import com.chad.hlife.ui.juhe.activity.BooksStoreActivity;
import com.chad.hlife.ui.juhe.activity.FilmTicketActivity;
import com.chad.hlife.ui.juhe.activity.HistoryDetailActivity;
import com.chad.hlife.ui.juhe.activity.NewsDetailActivity;
import com.chad.hlife.ui.zhihu.activity.CommentsActivity;
import com.chad.hlife.ui.zhihu.activity.DetailActivity;
import com.chad.hlife.ui.zhihu.activity.SectionsDetailActivity;
import com.chad.hlife.ui.zhihu.activity.ThemesDetailActivity;
import com.chad.hlife.ui.zhihu.activity.ZhiHuActivity;

import java.util.ArrayList;

public class ActivityHelper {

    public static void startMainActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void startNewsDetailActivity(Activity activity, String title, String url) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, NewsDetailActivity.class);
        intent.putExtra(AppConstant.EXTRA_TITLE, title);
        intent.putExtra(AppConstant.EXTRA_URL, url);
        activity.startActivity(intent);
    }

    public static void startHistoryDetailActivity(Activity activity, String title, String id) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, HistoryDetailActivity.class);
        intent.putExtra(AppConstant.EXTRA_TITLE, title);
        intent.putExtra(AppConstant.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    public static void startZhiHuActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ZhiHuActivity.class);
        activity.startActivity(intent);
    }

    public static void startFilmTicketActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, FilmTicketActivity.class);
        activity.startActivity(intent);
    }

    public static void startBooksStoreActivity(Activity activity, String title, int id) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, BooksStoreActivity.class);
        intent.putExtra(AppConstant.EXTRA_TITLE, title);
        intent.putExtra(AppConstant.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    public static void startBooksDetailActivity(Context context, String url) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, BooksDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(AppConstant.EXTRA_URL, url);
        context.startActivity(intent);
    }

    public static void startDetailActivity(Activity activity, ArrayList<Integer> storyIds, int id) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putIntegerArrayListExtra(AppConstant.EXTRA_LIST_ID, storyIds);
        intent.putExtra(AppConstant.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    public static void startCommentsActivity(Activity activity, int commentsCount, int longCommentsCount,
                                             int shortCommentsCount, int id) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, CommentsActivity.class);
        intent.putExtra(AppConstant.EXTRA_COMMENTS_COUNT, commentsCount);
        intent.putExtra(AppConstant.EXTRA_COMMENTS_COUNT_LONG, longCommentsCount);
        intent.putExtra(AppConstant.EXTRA_COMMENTS_COUNT_SHORT, shortCommentsCount);
        intent.putExtra(AppConstant.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    public static void startThemesDetailActivity(Activity activity, String title, int id) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, ThemesDetailActivity.class);
        intent.putExtra(AppConstant.EXTRA_TITLE, title);
        intent.putExtra(AppConstant.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    public static void startSectionsDetailActivity(Activity activity, String title, int id) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, SectionsDetailActivity.class);
        intent.putExtra(AppConstant.EXTRA_TITLE, title);
        intent.putExtra(AppConstant.EXTRA_ID, id);
        activity.startActivity(intent);
    }
}
