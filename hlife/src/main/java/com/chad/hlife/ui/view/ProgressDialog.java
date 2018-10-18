package com.chad.hlife.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.chad.hlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressDialog extends ConstraintLayout implements Animation.AnimationListener {

    @BindView(R.id.image_progress)
    AppCompatImageView mImageProgress;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;

    private Animation mAnimation;

    public ProgressDialog(Context context) {
        this(context, null);
    }

    public ProgressDialog(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressDialog(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_dialog_proress, this, true);
        ButterKnife.bind(this);
        setVisibility(GONE);
    }

    public void setTitle(String title) {
        mTextTitle.setText(title);
    }

    public boolean isShowing() {
        return getVisibility() == VISIBLE;
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        setVisibility(VISIBLE);
        startProgressAnimation();
    }

    public void dismiss() {
        if (!isShowing()) {
            return;
        }
        stopProgressAnimation();
        setVisibility(GONE);
    }

    private void startProgressAnimation() {
        if (mAnimation == null) {
            mAnimation = new RotateAnimation(0f, 360f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
        }
        mAnimation.setRepeatCount(1);
        mAnimation.setDuration(2000);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setAnimationListener(this);
        mImageProgress.startAnimation(mAnimation);
    }

    private void stopProgressAnimation() {
        if (mAnimation != null) {
            mAnimation.cancel();
            mAnimation.setAnimationListener(null);
            mAnimation = null;
        }
        mImageProgress.clearAnimation();
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
