package com.chad.hlife.ui.view.refresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.chad.hlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderView extends ConstraintLayout implements Animation.AnimationListener {

    @BindView(R.id.image_arrow)
    AppCompatImageView mImageArrow;
    @BindView(R.id.image_progress)
    AppCompatImageView mImageProgress;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;

    private Animation mAnimation;

    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_refresh_header, this, true);
        ButterKnife.bind(this);
    }

    public void refresh() {
        mImageArrow.setVisibility(GONE);
        mTextTitle.setText(R.string.refreshing);
        startProgressAnimation();
    }

    public void pullEnable(boolean enable) {
        mImageArrow.setVisibility(View.VISIBLE);
        mImageArrow.setRotation(enable ? 180 : 0);
        mTextTitle.setText(enable ? R.string.release_update : R.string.drop_down_refresh);
        stopProgressAnimation();
    }

    private void startProgressAnimation() {
        mImageProgress.setVisibility(VISIBLE);
        if (mAnimation == null) {
            mAnimation = new RotateAnimation(0f, 360f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            mAnimation.cancel();
            mAnimation.setInterpolator(null);
            mAnimation.setAnimationListener(null);
        }
        mAnimation.setRepeatCount(1);
        mAnimation.setDuration(1000);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setAnimationListener(this);
        mImageProgress.startAnimation(mAnimation);
    }

    private void stopProgressAnimation() {
        if (mAnimation != null) {
            mAnimation.cancel();
            mAnimation.setInterpolator(null);
            mAnimation.setAnimationListener(null);
            mAnimation = null;
        }
        mImageProgress.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == null) {
            return;
        }
        animation.reset();
        animation.setAnimationListener(this);
        animation.start();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
