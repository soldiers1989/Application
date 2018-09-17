package com.chad.zhihu.ui.view.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;

public class DoubleCircleLoadingAnimator extends LoadingAnimator {

    private static final int ANGLE_CIRCLE_OUT = 270;
    private static final int ANGLE_CIRCLE_IN = 90;

    private RectF mCircleOuter;
    private RectF mCircleInter;
    private Paint mPaint;

    private int mRotateAngle;

    @Override
    protected void initConfig(Context context) {
        float outerSize = getViewSize();
        float interSize = outerSize * 0.6f;
        initPaint(interSize * 0.3f);
        initDoubleCircle(outerSize, interSize);
    }

    private void initPaint(float strokeWidth) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(Color.BLACK);
        mPaint.setDither(true);
        mPaint.setFilterBitmap(true);
    }

    private void initDoubleCircle(float outerSize, float interSize) {
        mCircleOuter = new RectF();
        mCircleOuter.set(getCenterX() - outerSize, getCenterY() - outerSize,
                getCenterX() + outerSize, getCenterY() + outerSize);
        mCircleInter = new RectF();
        mCircleInter.set(getCenterX() - interSize, getCenterY() - interSize,
                getCenterX() + interSize, getCenterY() + interSize);
    }

    @Override
    protected void draw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        canvas.save();
        canvas.drawArc(mCircleOuter, mRotateAngle % 360, ANGLE_CIRCLE_OUT,
                false, mPaint);
        canvas.drawArc(mCircleInter, 270 - mRotateAngle % 360, ANGLE_CIRCLE_IN,
                false, mPaint);
        canvas.restore();
    }

    @Override
    protected void setAlpha(int alpha) {
        if (mPaint != null) {
            mPaint.setAlpha(alpha);
        }
    }

    @Override
    protected void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (mPaint != null) {
            mPaint.setColorFilter(colorFilter);
        }
    }

    @Override
    protected void onAnimationUpdate(ValueAnimator animation, float value) {
        mRotateAngle = (int) (360 * value);
    }
}
