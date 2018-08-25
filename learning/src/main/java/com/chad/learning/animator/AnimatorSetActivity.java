package com.chad.learning.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;

public class AnimatorSetActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.image_animator)
    AppCompatImageView mImageAnimator;
    @BindView(R.id.btn_play_together)
    AppCompatButton mBtnPlayTogether;
    @BindView(R.id.btn_play_with_after)
    AppCompatButton mBtnPlayWithAfter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animator_set;
    }

    @Override
    public void initViews() {
        mBtnPlayTogether.setOnClickListener(this);
        mBtnPlayWithAfter.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_together:
                playTogetherAnimation();
                break;
            case R.id.btn_play_with_after:
                playWithAfterAnimation();
                break;
            default:
                break;
        }
    }

    /**
     * 同时执行两个动画
     */
    private void playTogetherAnimation() {
        ObjectAnimator objectAnimatorOne = ObjectAnimator.ofFloat(mImageAnimator,
                "scaleX", 1.0F, 2.0F);
        ObjectAnimator objectAnimatorTwo = ObjectAnimator.ofFloat(mImageAnimator,
                "scaleY", 1.0F, 2.0F);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(objectAnimatorOne, objectAnimatorTwo);
        animatorSet.start();
    }

    /**
     * 动画One、TWO同时执行，接着Three执行，最后Four执行
     */
    private void playWithAfterAnimation() {
        float imageX = mImageAnimator.getX();
        ObjectAnimator objectAnimatorOne = ObjectAnimator.ofFloat(mImageAnimator,
                "scaleX", 1.0F, 2.0F);
        ObjectAnimator objectAnimatorTwo = ObjectAnimator.ofFloat(mImageAnimator,
                "scaleY", 1.0F, 2.0F);
        ObjectAnimator objectAnimatorThree = ObjectAnimator.ofFloat(mImageAnimator,
                "x", imageX, 0.0F);
        ObjectAnimator objectAnimatorFour = ObjectAnimator.ofFloat(mImageAnimator,
                "x", imageX);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.play(objectAnimatorOne).with(objectAnimatorTwo);
        animatorSet.play(objectAnimatorThree).after(objectAnimatorTwo);
        animatorSet.play(objectAnimatorFour).after(objectAnimatorThree);
        animatorSet.start();
    }
}
