package com.chad.weibo.glide;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 只有继承AppGlideModule，并添加@GlideModule，才可以使用GlideApp，也可以自己定义方法
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {

    public static void load(Context context, String url, AppCompatImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void load(Activity activity, String url, AppCompatImageView imageView) {
        GlideApp.with(activity)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void load(FragmentActivity fragmentActivity, String url, AppCompatImageView imageView) {
        GlideApp.with(fragmentActivity)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void load(Fragment fragment, String url, AppCompatImageView imageView) {
        GlideApp.with(fragment)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void load(View view, String url, AppCompatImageView imageView) {
        GlideApp.with(view)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
