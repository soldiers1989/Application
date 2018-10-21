package com.chad.hlife.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class NestedExpandableListView extends ExpandableListView {

    public NestedExpandableListView(Context context) {
        this(context, null);
    }

    public NestedExpandableListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
