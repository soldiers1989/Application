package com.chad.zhihu.ui.view.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LoadingDrawable extends Drawable implements Drawable.Callback, Animatable {

    private final LoadingAnimator mLoadingAnimator;

    public LoadingDrawable(Context context, LoadingAnimator animator) {
        mLoadingAnimator = animator;
        mLoadingAnimator.setDrawableCallback(this);
        mLoadingAnimator.init(context);
        mLoadingAnimator.initConfig(context);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (!getBounds().isEmpty() && mLoadingAnimator != null) {
            mLoadingAnimator.draw(canvas);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if (mLoadingAnimator != null) {
            mLoadingAnimator.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (mLoadingAnimator != null) {
            mLoadingAnimator.setColorFilter(colorFilter);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void start() {
        if (mLoadingAnimator != null) {
            mLoadingAnimator.start();
        }
    }

    @Override
    public void stop() {
        if (mLoadingAnimator != null) {
            mLoadingAnimator.stop();
        }
    }

    @Override
    public boolean isRunning() {
        return mLoadingAnimator == null? false: mLoadingAnimator.isRunning();
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        invalidateSelf();
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        scheduleSelf(what, when);
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        unscheduleSelf(what);
    }
}
