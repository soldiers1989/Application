package com.chad.hlife.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.chad.hlife.R;

public class LetterIndexView extends View {

    private Paint letterPaint;
    private OnLetterChangedListener onLetterChangedListener;

    private String[] letters;

    private int itemWidth;
    private int itemHeight;
    private int currentIndex;

    public interface OnLetterChangedListener {
        void onLetterChanged(String letter);
    }

    public LetterIndexView(Context context) {
        this(context, null);
    }

    public LetterIndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public LetterIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        letterPaint = new Paint();
        letterPaint.setTextSize(40);
        letterPaint.setAntiAlias(true);
        letters = context.getResources().getStringArray(R.array.letters);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight() / letters.length;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < letters.length; i++) {
            if (i == currentIndex) {
                letterPaint.setColor(getResources().getColor(R.color.colorPrimary));
            } else {
                letterPaint.setColor(getResources().getColor(R.color.colorText));
            }
            Rect rect = new Rect();
            letterPaint.getTextBounds(letters[i], 0, 1, rect);
            int letterWidth = rect.width();
            float letterX = itemWidth / 2 - letterWidth / 2;
            float letterY = itemWidth / 2 + i * itemHeight;
            canvas.drawText(letters[i], letterX, letterY, letterPaint);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int index = (int) (event.getY() / itemHeight);
                if (index != currentIndex) {
                    currentIndex = index;
                }
                if (onLetterChangedListener != null
                        && 0 <= currentIndex
                        && currentIndex <= letters.length - 1) {
                    onLetterChangedListener.onLetterChanged(letters[currentIndex]);
                }
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public void setOnLetterChangedListener(OnLetterChangedListener listener) {
        onLetterChangedListener = listener;
    }

    public void setLetterIndex(String word) {
        if (TextUtils.isEmpty(word) || letters == null) {
            return;
        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i].equals(word)) {
                currentIndex = i;
                invalidate();
                return;
            }
        }
    }
}
