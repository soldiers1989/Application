package com.chad.learning.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;

/**
 * Created by jxzhang on 2017/8/17.
 */

public class ObjectAnimatorActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.image_animator)
    AppCompatImageView mImageAnimator;
    @BindView(R.id.btn_rotate)
    AppCompatButton mBtnRotate;
    @BindView(R.id.btn_alpha_scale)
    AppCompatButton mBtnAlphaScale;
    @BindView(R.id.btn_scale_alpha)
    AppCompatButton mBtnScaleAlpha;
    @BindView(R.id.btn_alpha_remove)
    AppCompatButton mBtnAlphaRemove;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animator_object;
    }

    @Override
    public void initViews() {
        mBtnRotate.setOnClickListener(this);
        mBtnAlphaScale.setOnClickListener(this);
        mBtnScaleAlpha.setOnClickListener(this);
        mBtnAlphaRemove.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rotate:
                rotateAnimation();
                break;
            case R.id.btn_alpha_scale:
                alphaScaleAnimation();
                break;
            case R.id.btn_scale_alpha:
                scaleAlphaAnimation();
            case R.id.btn_alpha_remove:
                alphaRemoveAnimation();
                break;
            default:
                break;
        }
    }

    /**
     * 360度翻转
     */
    private void rotateAnimation() {
        ObjectAnimator
                .ofFloat(mImageAnimator, "rotationX", 0.0F, 360.0F)
                .setDuration(500)
                .start();
    }

    /**
     * 缩小并淡出后放大并淡入
     */
    private void alphaScaleAnimation() {
        PropertyValuesHolder alpha = PropertyValuesHolder
                .ofFloat("alpha", 1.0F, 0.0F, 1.0F);
        PropertyValuesHolder scaleX = PropertyValuesHolder
                .ofFloat("scaleX", 1.0F, 0.0F, 1.0F);
        PropertyValuesHolder scaleY = PropertyValuesHolder
                .ofFloat("scaleY", 1.0F, 0.0F, 1.0F);
        ObjectAnimator.ofPropertyValuesHolder(mImageAnimator, alpha, scaleX, scaleY)
                .setDuration(500)
                .start();
    }

    /**
     * 缩小并淡出
     */
    private void scaleAlphaAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(mImageAnimator, "chad", 1.0F, 0.0F)
                .setDuration(500);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ScaleAlphaUpdateListener());
    }

    /**
     * 淡出并移除控件
     */
    private void alphaRemoveAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(mImageAnimator, "alpha", 0.5F);
        objectAnimator.addListener(new AlphaRemoveAnimationListener());
        objectAnimator.start();
    }

    private class ScaleAlphaUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            mImageAnimator.setAlpha(animatedValue);
            mImageAnimator.setScaleX(animatedValue);
            mImageAnimator.setScaleY(animatedValue);
        }
    }

    private class AlphaRemoveAnimationListener extends AnimatorListenerAdapter {
        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            ViewGroup viewGroup = (ViewGroup) mImageAnimator.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mImageAnimator);
            }
        }
    }
}
