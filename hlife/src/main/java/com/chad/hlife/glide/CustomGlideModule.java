package com.chad.hlife.glide;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
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
}
