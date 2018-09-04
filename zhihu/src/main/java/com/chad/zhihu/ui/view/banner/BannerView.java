package com.chad.zhihu.ui.view.banner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.zhihu.R;
import com.chad.zhihu.hepler.glide.GlideApp;
import com.chad.zhihu.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BannerView extends ConstraintLayout implements ViewPager.OnPageChangeListener,
        BannerAdapter.OnItemClickListener {

    @BindView(R.id.pager_image)
    ViewPager mImagePager;
    @BindView(R.id.text_title)
    AppCompatTextView mTextTitle;
    @BindView(R.id.layout_points)
    LinearLayout mPointsLayout;

    private Unbinder unbinder = null;
    private BannerAdapter bannerAdapter = null;
    private OnItemClickListener onItemClickListener = null;
    private Disposable disposable = null;

    private List<Banner> bannerList = null;
    private List<AppCompatImageView> imageViewList = null;

    private int normalPointResourceId = R.drawable.ic_banner_point_normal; // 未选中指示器
    private int selectedPointResourceId = R.drawable.ic_banner_point_selected;  // 选中指示器
    private int time = 5; // 轮播时间
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (listener == null) {
            return;
        }
        onItemClickListener = listener;
    }

    public void setBannerList(List<Banner> bannerList) {
        if (this.bannerList == null) {
            this.bannerList = new ArrayList<>();
        }
        this.bannerList.addAll(bannerList);
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
            int height = DisplayUtil.dp2Px(getContext(), 5);
            int width = DisplayUtil.dp2Px(getContext(), 5);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            layoutParams.leftMargin = 10;
            point.setLayoutParams(layoutParams);
            point.setEnabled(false);
            mPointsLayout.addView(point);
            // 初始化ImageView
            AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
            String image = bannerList.get(i).getImage();
            if (!TextUtils.isEmpty(image)) {
                GlideApp.with(getContext())
                        .load(image)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(appCompatImageView);
            }
            imageViewList.add(appCompatImageView);
        }

        mPointsLayout.getChildAt(0).setBackgroundResource(selectedPointResourceId);
        mTextTitle.setText(bannerList.get(0).getTitle());

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

    public void stop() {
        stopScroll();
    }

    private void startScroll() {
        if (isStartScroll) {
            return;
        }
        isStartScroll = true;
        disposable = Observable.timer(time, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(aLong -> {
                    if (!isStartScroll) {
                        return;
                    }
                    isStartScroll = false;
                    mImagePager.setCurrentItem(mImagePager.getCurrentItem() + 1);
                });
    }

    private void stopScroll() {
        if (!isStartScroll) {
            return;
        }
        isStartScroll = false;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        position = position % mPointsLayout.getChildCount();
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
            onItemClickListener.onItemClick(currentIndex);
        }
    }
}
