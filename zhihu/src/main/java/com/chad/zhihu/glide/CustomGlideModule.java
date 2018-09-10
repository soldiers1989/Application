package com.chad.zhihu.glide;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.chad.zhihu.R;

/**
 * 只有继承AppGlideModule，并添加@GlideModule，才可以使用GlideApp，也可以自己定义方法
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {

    public static void loadImage(Context context, String url, AppCompatImageView imageView) {
        GlideApp.with(context)
                .load(url) // 设置URL
                .centerCrop() // 设置scaleType
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 设置缓存
                .placeholder(R.drawable.pic_default_placeholder) // 设置占位图
                .into(imageView);
    }
}