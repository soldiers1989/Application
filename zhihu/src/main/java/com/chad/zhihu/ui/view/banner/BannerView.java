package com.chad.zhihu.ui.view.banner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.zhihu.R;
import com.chad.zhihu.hepler.GlideHelper;
import com.chad.zhihu.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BannerView extends ConstraintLayout implements ViewPager.OnPageChangeListener, BannerAdapter.OnItemClickListener {

    @BindView(R.id.pager_image)
    ViewPager mImagePager;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;
    @BindView(R.id.layout_points)
    LinearLayout mPointsLayout;

    private Unbinder unbinder = null;
    private BannerAdapter bannerAdapter = null;
    private OnItemClickListener onItemClickListener = null;

    private List<Banner> bannerList = null;
    private List<AppCompatImageView> imageViewList = null;

    private int normalPointResourceId = R.drawable.ic_banner_point_normal; // 未选中指示器
    private int selectedPointResourceId = R.drawable.ic_banner_point_selected;  // 选中指示器
    private int timeDelay = 10; // 轮播时间
    private int currentIndex = 0; // 当前轮播位置

    private boolean isStartScroll = false;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_banner, this, true);
        unbinder = ButterKnife.bind(this);
    }

    public void setBannerList(List<Banner> bannerList) {
        if (this.bannerList == null) {
            this.bannerList = new ArrayList<>();
        }
        this.bannerList.addAll(bannerList);
    }

    public void setNormalPointResourceId(int normalPointResourceId) {
        this.normalPointResourceId = normalPointResourceId;
    }

    public void setSelectedPointResourceId(int selectedPointResourceId) {
        this.selectedPointResourceId = selectedPointResourceId;
    }

    public void setTime(int time) {
        this.timeDelay = timeDelay;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (listener == null) {
            return;
        }
        onItemClickListener = listener;
    }

    public void start() {
        if (bannerList == null || bannerList.size() == 0) {
            setVisibility(GONE);
            return;
        }

        int size = bannerList.size();
        if (mPointsLayout.getChildCount() > 0) {
            mPointsLayout.removeAllViewsInLayout();
        }
        if (imageViewList == null) {
            imageViewList = new ArrayList<>();
        } else {
            imageViewList.clear();
        }
        for (int i = 0; i < size; i++) {
            // 初始化Point
            View point = new View(getContext());
            point.setBackgroundResource(normalPointResourceId);
            int height = DisplayUtil.dpToPx(getContext(), 5);
            int width = DisplayUtil.dpToPx(getContext(), 5);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            layoutParams.leftMargin = 10;
            point.setLayoutParams(layoutParams);
            point.setEnabled(false);
            mPointsLayout.addView(point);
            // 初始化ImageView
            AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
            GlideHelper.loadBannerImage(bannerList.get(i).getImage(), appCompatImageView);
            imageViewList.add(appCompatImageView);
        }

        mImagePager.clearOnPageChangeListeners();
        mImagePager.addOnPageChangeListener(this);
        if (bannerAdapter == null) {
            bannerAdapter = new BannerAdapter(imageViewList);
            bannerAdapter.setOnItemClickListener(this);
            mImagePager.setAdapter(bannerAdapter);
        } else {
            bannerAdapter.setImageViewList(imageViewList);
        }

        startScroll();
    }

    private void startScroll() {
        if (isStartScroll) {
            return;
        }
        isStartScroll = true;
    }

    private void stopScroll() {
        if (!isStartScroll) {
            return;
        }
        isStartScroll = false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        currentIndex = position;
        for (int i = 0; i < mPointsLayout.getChildCount(); i++) {
            if (currentIndex == i) {
                mPointsLayout.getChildAt(i).setBackgroundResource(selectedPointResourceId);
                mTextTitle.setText(bannerList.get(i).getTitle());
            } else {
                mPointsLayout.getChildAt(i).setBackgroundResource(normalPointResourceId);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                if (!isStartScroll) {
                    startScroll();
                }
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
                if (isStartScroll) {
                    stopScroll();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(position);
        }
    }
}
