package com.chad.zhihu.hepler;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.chad.zhihu.ZhiHuApplication;
import com.chad.zhihu.dagger.app.AppComponent;
import com.chad.zhihu.util.LogUtil;

public class GlideHelper {

    private static final String TAG = GlideHelper.class.getSimpleName();

    private static Context context = null;

    static {
        initContext();
    }

    private static void initContext() {
        LogUtil.d(TAG, "initContext : context = " + context);
        if (context == null) {
            AppComponent appComponent = ZhiHuApplication.getAppComponent();
            LogUtil.d(TAG, "initContext : appComponent = " + appComponent);
            if (appComponent != null) {
                context = appComponent.context();
            }
        }
    }

    public static void loadBannerImage(String url, AppCompatImageView imageView) {
        if (context == null || TextUtils.isEmpty(url) || imageView == null) {
            return;
        }
        Glide.with(context).load(url).into(imageView);
    }
}
