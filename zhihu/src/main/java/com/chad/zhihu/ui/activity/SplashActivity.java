package com.chad.zhihu.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.AppCompatImageView;

import com.chad.zhihu.R;
import com.chad.zhihu.hepler.ActivityHelper;
import com.chad.zhihu.ui.base.BaseRxAppCompatActivity;
import com.chad.zhihu.util.LogUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashActivity extends BaseRxAppCompatActivity implements Animator.AnimatorListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.image_splash)
    AppCompatImageView mImageSplash;

    private ObjectAnimator objectAnimatorX = null;
    private ObjectAnimator objectAnimatorY = null;
    private AnimatorSet animatorSet = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
    }

    @Override
    protected void initData() {
        Observable.timer(2000, TimeUnit.MILLISECONDS) // 被观察者延迟两秒发送事件
                .compose(bindToLifecycle()) //自动取消订阅
                .observeOn(AndroidSchedulers.mainThread()) // 观察者在mainThread处理事件
                .subscribe(aLong -> startAnimator());   // 订阅
        LogUtil.d(TAG, "initData");
    }

    private void startAnimator() {
        objectAnimatorX = ObjectAnimator.ofFloat(mImageSplash, "scaleX", 1f, 1.3f);
        objectAnimatorY = ObjectAnimator.ofFloat(mImageSplash, "scaleY", 1f, 1.3f);
        animatorSet = new AnimatorSet();
        animatorSet.setDuration(3000);
        animatorSet.play(objectAnimatorX).with(objectAnimatorY);
        animatorSet.addListener(this);
        animatorSet.start();
        LogUtil.d(TAG, "startAnimator");
    }

    @Override
    public void onAnimationStart(Animator animation) {
        LogUtil.d(TAG, "startAnimator");
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        ActivityHelper.startMainActivity(this);
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
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        LogUtil.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        animatorSet = null;
        objectAnimatorX = null;
        objectAnimatorY = null;
        LogUtil.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
