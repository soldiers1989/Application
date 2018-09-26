package com.chad.hlife.ui.view.refresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.chad.hlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderView extends ConstraintLayout {

    @BindView(R.id.image_arrow)
    AppCompatImageView mImageArrow;
    @BindView(R.id.bar_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;

    private Context mContext;

    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_refresh_header, this, true);
        ButterKnife.bind(this);
    }

    public void refresh() {
        mImageArrow.setVisibility(GONE);
        mProgressBar.setVisibility(INVISIBLE);
        mTextTitle.setText("刷新中...");
    }

    public void pullEnable(boolean enable) {
        mImageArrow.setVisibility(View.VISIBLE);
        mImageArrow.setRotation(enable ? 180 : 0);
        mProgressBar.setVisibility(View.GONE);
        mTextTitle.setText(enable ? "释放更新" : "下拉刷新");
    }
}
