package com.chad.hlife.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.chad.hlife.R;
import com.chad.hlife.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressDialog extends Dialog implements Animation.AnimationListener {

    private static final String TAG = ProgressDialog.class.getSimpleName();

    @BindView(R.id.image_progress)
    AppCompatImageView mImageProgress;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;

    private Animation mAnimation;

    private int mTitleResId;

    public ProgressDialog(Context context) {
        super(context, R.style.ProgressDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_proress);
        ButterKnife.bind(this);
        initWindow();
        initView();
    }

    private void initWindow() {
        LogUtil.d(TAG, "initWindow");
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
    }

    private void initView() {
        LogUtil.d(TAG, "initView");
        mTextTitle.setText(mTitleResId);
    }

    public void setTitle(int resId) {
        mTitleResId = resId;
    }

    @Override
    public void show() {
        super.show();
        startProgressAnimation();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        stopProgressAnimation();
    }

    private void startProgressAnimation() {
        if (mImageProgress == null) {
            return;
        }
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
        if (mImageProgress == null) {
            return;
        }
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
