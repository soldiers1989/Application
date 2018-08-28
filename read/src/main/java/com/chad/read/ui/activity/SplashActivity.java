package com.chad.read.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;

import com.chad.read.R;
import com.chad.read.app.constant.AppConstant;
import com.chad.read.ui.base.BaseAppCompatActivity;
import com.chad.read.util.LogUtil;


import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * 闪屏界面
 */
public class SplashActivity extends BaseAppCompatActivity implements Animator.AnimatorListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.image_splash)
    AppCompatImageView mImageSplash;

    private Subscription mSubscription = null;

    private ObjectAnimator mObjectAnimatorX = null;
    private ObjectAnimator mObjectAnimatorY = null;
    private AnimatorSet mAnimatorSet = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews() {
        LogUtil.d(TAG, "initViews");
    }

    @Override
    public void initData() {
        initSplashAnimator();
        LogUtil.d(TAG, "initData");
    }

    private void initSplashAnimator () {
        mSubscription = Observable.timer(AppConstant.TIME_SPLASH_ANIMATOR_TIMER, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.from(new Executor() {
                    @Override
                    public void execute(@NonNull Runnable runnable) {
                        runOnUiThread(runnable);
                    }
                }))
                .subscribe(aLong -> startSplashAnimator());
        LogUtil.d(TAG, "initSplashAnimator");
    }

    private void startSplashAnimator() {
        mObjectAnimatorX = ObjectAnimator.ofFloat(mImageSplash, "scaleX", 1f, 1.2f);
        mObjectAnimatorY = ObjectAnimator.ofFloat(mImageSplash, "scaleY", 1f, 1.2f);
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.addListener(this);
        mAnimatorSet.play(mObjectAnimatorX).with(mObjectAnimatorY);
        mAnimatorSet.setDuration(AppConstant.TIME_SPLASH_ANIMATOR_DURATION);
        mAnimatorSet.start();
        LogUtil.d(TAG, "startSplashAnimator");
    }

    @Override
    public void onAnimationStart(Animator animation) {
        LogUtil.d(TAG, "onAnimationStart");
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        LogUtil.d(TAG, "onAnimationEnd");
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        LogUtil.d(TAG, "onAnimationCancel");
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        LogUtil.d(TAG, "onAnimationRepeat");
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        LogUtil.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
        if (mAnimatorSet != null) {
            mAnimatorSet = null;
        }
        if (mObjectAnimatorX != null) {
            mObjectAnimatorX = null;
        }
        if (mObjectAnimatorY != null) {
            mObjectAnimatorY = null;
        }
        LogUtil.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
