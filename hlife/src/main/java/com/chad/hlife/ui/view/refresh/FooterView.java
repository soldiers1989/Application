package com.chad.hlife.ui.view.refresh;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.chad.hlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FooterView extends ConstraintLayout implements Animation.AnimationListener {

    @BindView(R.id.image_progress)
    AppCompatImageView mImageProgress;

    private Animation mAnimation;

    public FooterView(Context context) {
        this(context, null);
    }

    public FooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_refresh_footer, this, true);
        ButterKnife.bind(this);
        startProgressAnimation();
    }

    private void startProgressAnimation() {
        mImageProgress.setVisibility(VISIBLE);
        if (mAnimation == null) {
            mAnimation = new RotateAnimation(0f, 360f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
        }
        mAnimation.setRepeatCount(1);
        mAnimation.setDuration(1000);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setAnimationListener(this);
        mImageProgress.startAnimation(mAnimation);
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
