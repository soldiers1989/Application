package com.chad.zhihu.ui.view.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chad.zhihu.util.DisplayUtil;

public class DoubleCircleLoading extends View {

    private static final int ANGLE_CIRCLE_OUTER = 270; // 外面的圆可以转动的最大角度
    private static final int ANGLE_CIRCLE_INTER = 90;  // 里面的圆可以转动的最大角度

    private static final float DEFAULT_SIZE = 56.0f;   // 默认大小

    private Paint mPaint = null;
    private RectF mRectFCircleOuter = null;
    private RectF mRectFCircleInter = null;

    private int mAngleRotate;

    private float mWidth;
    private float mHeight;

    public DoubleCircleLoading(Context context) {
        this(context, null);
    }

    public DoubleCircleLoading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleCircleLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mWidth = DisplayUtil.dip2px(context, DEFAULT_SIZE);
        mHeight = DisplayUtil.dip2px(context, DEFAULT_SIZE);
        float outerSize = DisplayUtil.dip2px(context, DEFAULT_SIZE * 0.5f - 10);
        float interSize = outerSize * 0.6f;
        initPaint(interSize * 0.4f);
        initRectFCircle(outerSize, interSize);
    }

    private void initPaint(float width) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(width);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setColor(Color.GREEN);
        mPaint.setDither(true);
        mPaint.setFilterBitmap(true);
    }

    private void initRectFCircle(float outerSize, float interSize) {
        mAngleRotate = 0;
        mRectFCircleOuter = new RectF();
        mRectFCircleOuter.set(getCenterX() - outerSize, getCenterY() - outerSize,
                getCenterX() + outerSize, getCenterY() + outerSize);
        mRectFCircleInter = new RectF();
        mRectFCircleInter.set(getCenterX() - interSize, getCenterY() - interSize,
                getCenterX() + interSize, getCenterY() + interSize);
    }

    private float getCenterX() {
        return mWidth * 0.5f;
    }

    private float getCenterY() {
        return mHeight * 0.5f;
    }

    public void setAlpha(int alpha) {
        if (mPaint == null) {
            return;
        }
        mPaint.setAlpha(alpha);
    }
}
