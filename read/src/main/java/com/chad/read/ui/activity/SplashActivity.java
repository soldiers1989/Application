package com.chad.read.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;

import com.chad.read.R;
import com.chad.read.app.Const;
import com.chad.read.ui.base.BaseAppCompatActivity;


import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashActivity extends BaseAppCompatActivity implements Animator.AnimatorListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.image_splash)
    AppCompatImageView mImageSplash;

    private ObjectAnimator mObjectAnimatorX = null;
    private ObjectAnimator mObjectAnimatorY = null;
    private AnimatorSet mAnimatorSet = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {
        initSplashAnimator();
    }

    private void initSplashAnimator () {
        Observable.timer(Const.TIME_SPLASH_ANIMATOR_TIMER, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> startSplashAnimator());
    }

    private void startSplashAnimator() {
        mObjectAnimatorX = ObjectAnimator.ofFloat(mImageSplash, "scaleX", 1f, 1.2f);
        mObjectAnimatorY = ObjectAnimator.ofFloat(mImageSplash, "scaleY", 1f, 1.2f);
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.addListener(this);
        mAnimatorSet.play(mObjectAnimatorX).with(mObjectAnimatorY);
        mAnimatorSet.setDuration(Const.TIME_SPLASH_ANIMATOR_DURATION);
        mAnimatorSet.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        if (mAnimatorSet != null) {
            mAnimatorSet = null;
        }
        if (mObjectAnimatorX != null) {
            mObjectAnimatorX = null;
        }
        if (mObjectAnimatorY != null) {
            mObjectAnimatorY = null;
        }
        super.onDestroy();
    }
}
