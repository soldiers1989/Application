package com.chad.hlife.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.chad.hlife.app.AppConstant;
import com.chad.hlife.ui.activity.BooksDetailActivity;
import com.chad.hlife.ui.activity.BooksStoreActivity;
import com.chad.hlife.ui.activity.FilmTicketActivity;
import com.chad.hlife.ui.activity.HistoryDetailActivity;
import com.chad.hlife.ui.activity.NewsDetailActivity;

public class ActivityHelper {

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
}
