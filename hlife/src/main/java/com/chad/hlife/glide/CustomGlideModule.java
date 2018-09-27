package com.chad.hlife.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.hlife.R;

/**
 * 只有继承AppGlideModule，并添加@GlideModule，才可以使用GlideApp，也可以自己定义方法
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {

    public static void loadCenterCrop(Context context, String url, AppCompatImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.pic_placeholder_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadFitCenter(Context context, String url, AppCompatImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.pic_placeholder_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadFitXY(Context context, String url, AppCompatImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .fitCenter()
                .placeholder(R.drawable.pic_placeholder_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
