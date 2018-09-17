package com.chad.zhihu.ui.view.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

public abstract class LoadingAnimator implements ValueAnimator.AnimatorUpdateListener {

    protected static final float DEFAULT_VIEW_SIZE = 60.0f;

    protected static final long TIME_ANIMATION_START_DELAY = 50;
    protected static final long TIME_ANIMATION_DURATION = 1000;

    private Drawable.Callback mDrawableCallback = null;
    private ValueAnimator mValueAnimator = null;

    private float mViewSize;
    private float mViewWidth;
    private float mViewHeight;

    private double mDurationPercent = 1.0f;

    public void init(Context context) {
        initViewSize(context);
        initValueAnimator();
    }

    public void setDrawableCallback(Drawable.Callback drawableCallback) {
        mDrawableCallback = drawableCallback;
    }

    public void setDurationPercent(double percent) {
        if (percent <= 0f) {
            mDurationPercent = 1.0f;
        } else {
            mDurationPercent = percent;
        }
    }

    public void start() {
        if (mValueAnimator == null || mValueAnimator.isStarted()) {
            return;
        }
        mValueAnimator.addUpdateListener(this);
        mValueAnimator.setRepeatCount(Animation.INFINITE);
        mValueAnimator.setDuration(getAnimationDuration());
        mValueAnimator.start();
    }

    public void stop() {
        if (mValueAnimator == null || !mValueAnimator.isStarted()) {
            return;
        }
        mValueAnimator.removeAllUpdateListeners();
        mValueAnimator.end();
    }

    public boolean isRunning() {
        return mValueAnimator == null ? false : mValueAnimator.isRunning();
    }

    private void initViewSize(Context context) {
        mViewSize = dip2px(context, DEFAULT_VIEW_SIZE * 0.5f - 10);
        mViewWidth = dip2px(context, DEFAULT_VIEW_SIZE);
        mViewHeight = dip2px(context, DEFAULT_VIEW_SIZE);
    }

    private void initValueAnimator() {
        mValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        mValueAnimator.setStartDelay(getAnimationStartDelay());
        mValueAnimator.setInterpolator(new LinearInterpolator());
    }

    public float getViewSize() {
        return mViewSize;
    }

    public float getCenterX() {
        return mViewWidth * 0.5f;
    }

    public float getCenterY() {
        return mViewHeight * 0.5f;
    }

    protected long getAnimationStartDelay() {
        return TIME_ANIMATION_START_DELAY;
    }

    protected long getAnimationDuration() {
        return (long) Math.ceil(TIME_ANIMATION_DURATION * mDurationPercent);
    }

    protected static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        onAnimationUpdate(animation, (float) animation.getAnimatedValue());
        if (mDrawableCallback != null) {
            mDrawableCallback.invalidateDrawable(null);
        }
    }

    protected abstract void initConfig(Context context);

    protected abstract void draw(Canvas canvas);

    protected abstract void setAlpha(int alpha);

    protected abstract void setColorFilter(@Nullable ColorFilter colorFilter);

    protected abstract void onAnimationUpdate(ValueAnimator animation,
                                              @FloatRange(from = 0.0, to = 1.0) float value);
}
