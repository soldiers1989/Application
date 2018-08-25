package com.chad.learning.animator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;

public class ValueAnimatorActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.image_animator)
    AppCompatImageView mImageAnimator;
    @BindView(R.id.btn_vertical_move)
    AppCompatButton mBtnVerticalMove;
    @BindView(R.id.btn_parabolic_move)
    AppCompatButton mBtnParabolicMove;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animator_value;
    }

    @Override
    public void initViews() {
        mBtnVerticalMove.setOnClickListener(this);
        mBtnParabolicMove.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_vertical_move:
                verticalMoveAnimation();
                break;
            case R.id.btn_parabolic_move:
                parabolicMoveAnimation();
                break;
            default:
                break;
        }
    }

    /**
     * 垂直移动
     */
    private void verticalMoveAnimation() {
        ValueAnimator valueAnimator = ValueAnimator
                .ofFloat(0, getWindowHeight() - mImageAnimator.getHeight());
        valueAnimator.setDuration(500);
        valueAnimator.setTarget(mImageAnimator);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new VerticalUpdateListener());
    }

    /**
     * 抛物线运动
     */
    private void parabolicMoveAnimation() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new ParabolicTypeEvaluator());
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ParabolicUpdateListener());
    }

    private int getWindowHeight() {
        WindowManager windowManager = getWindowManager();
        return windowManager.getDefaultDisplay().getHeight();
    }

    private class VerticalUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedValue = (float) valueAnimator.getAnimatedValue();
            mImageAnimator.setTranslationY(animatedValue);
        }
    }

    private class ParabolicUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            PointF animatedValue = (PointF) valueAnimator.getAnimatedValue();
            mImageAnimator.setX(animatedValue.x);
            mImageAnimator.setY(animatedValue.y);
        }
    }

    private class ParabolicTypeEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float v, PointF pointF, PointF t1) {
            // x方向200px/s ，则y方向0.5 * 10 * t
            PointF pointF1 = new PointF();
            pointF1.x = 200 * v * 3;
            pointF1.y = 0.5F * 200 * (v * 3) * (v * 3);
            return pointF1;
        }
    }
}
