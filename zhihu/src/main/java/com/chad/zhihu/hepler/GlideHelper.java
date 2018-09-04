package com.chad.zhihu.hepler;

import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.chad.zhihu.ZhiHuApplication;

public class GlideHelper {

    private static final String TAG = GlideHelper.class.getSimpleName();

    public static void loadImage(String url, AppCompatImageView imageView) {
        if (TextUtils.isEmpty(url) || imageView == null) {
            return;
        }
        Glide.with(ZhiHuApplication.getZhiHuApplication()).load(url).into(imageView);
    }
}
