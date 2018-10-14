package com.chad.hlife.ui.view.loading;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.chad.hlife.R;

public class DoubleCircleLoadingView extends AppCompatImageView {

    private LoadingAnimator mLoadingAnimator;
    private LoadingDrawable mLoadingDrawable;

    public DoubleCircleLoadingView(Context context) {
        this(context, null);
    }

    public DoubleCircleLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleCircleLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mLoadingAnimator = new DoubleCircleLoadingAnimator();
        mLoadingDrawable = new LoadingDrawable(context, mLoadingAnimator);
        setColorFilter(context.getResources().getColor(R.color.colorPrimary));
        setImageDrawable(mLoadingDrawable);
    }

    public void setColor(@ColorInt int color) {
        setColorFilter(color);
    }

    @Override
    protected void onAttachedToWindow() {
        if (mLoadingDrawable != null) {
            mLoadingDrawable.start();
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mLoadingDrawable != null) {
            mLoadingDrawable.stop();
        }
        super.onDetachedFromWindow();
    }
}
