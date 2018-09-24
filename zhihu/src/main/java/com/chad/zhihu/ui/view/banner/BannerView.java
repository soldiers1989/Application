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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.zhihu.R;
import com.chad.zhihu.app.AppSettings;
import com.chad.zhihu.glide.CustomGlideModule;
import com.chad.zhihu.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private BannerAdapter mBannerAdapter = null;
    private OnBannerItemClickListener mOnBannerItemClickListener = null;
    private Disposable mDisposable = null;

    private List<Banner> mBannerList = null;
    private List<AppCompatImageView> mImageViewList = null;

    private int mDelayTime = 10; // 轮播时间
    private int mCurrentIndex = 0; // 当前轮播位置

    private boolean isStartScroll = false;

    public interface OnBannerItemClickListener {
        void onBannerItemClick(int id);
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
        ButterKnife.bind(this);
    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener listener) {
        if (listener == null) {
            return;
        }
        mOnBannerItemClickListener = listener;
    }

    public void setBannerList(List<Banner> bannerList) {
        if (mBannerList == null) {
            mBannerList = new ArrayList<>();
        } else {
            mBannerList.clear();
        }
        mBannerList.addAll(bannerList);
    }

    public void start() {
        if (mBannerList == null || mBannerList.size() == 0) {
            setVisibility(GONE);
            return;
        }
        reset();
        Observable.fromIterable(mBannerList)
                .subscribe(banner -> {
                    // 初始化Point
                    View point = new View(getContext());
                    point.setBackgroundResource(R.drawable.ic_banner_point_normal);
                    int height = DisplayUtil.dp2Px(getContext(), 5);
                    int width = DisplayUtil.dp2Px(getContext(), 5);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
                    layoutParams.leftMargin = 10;
                    point.setLayoutParams(layoutParams);
                    point.setEnabled(false);
                    mPointsLayout.addView(point);
                    // 初始化ImageView
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    appCompatImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    appCompatImageView.setImageResource(R.drawable.pic_default_placeholder);
                    String image = banner.getImage();
                    if (!TextUtils.isEmpty(image)
                            && AppSettings.getInstance().isShowPicture()) {
                        CustomGlideModule.loadImage(getContext(), image, appCompatImageView);
                    }
                    mImageViewList.add(appCompatImageView);
                });

        mPointsLayout.getChildAt(0).setBackgroundResource(R.drawable.ic_banner_point_selected);
        mTextTitle.setText(mBannerList.get(0).getTitle());

        if (mBannerAdapter == null) {
            mBannerAdapter = new BannerAdapter(mImageViewList);
            mBannerAdapter.setOnItemClickListener(this);
            mImagePager.setAdapter(mBannerAdapter);
        } else {
            mBannerAdapter.setImageViewList(mImageViewList);
        }

        mImagePager.clearOnPageChangeListeners();
        mImagePager.addOnPageChangeListener(this);

        startScroll();
    }

    public void stop() {
        stopScroll();
    }

    private void reset() {
        if (mPointsLayout.getChildCount() > 0) {
            mPointsLayout.removeAllViewsInLayout();
        }
        if (mImageViewList == null) {
            mImageViewList = new ArrayList<>();
        } else {
            mImageViewList.clear();
        }
    }

    private void startScroll() {
        if (isStartScroll) {
            return;
        }
        isStartScroll = true;
        mDisposable = Observable.timer(mDelayTime, TimeUnit.SECONDS)
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
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        position = position % mPointsLayout.getChildCount();
        mCurrentIndex = position;
        for (int i = 0; i < mPointsLayout.getChildCount(); i++) {
            if (mCurrentIndex == i) {
                mPointsLayout.getChildAt(i).setBackgroundResource(R.drawable.ic_banner_point_selected);
                mTextTitle.setText(mBannerList.get(i).getTitle());
            } else {
                mPointsLayout.getChildAt(i).setBackgroundResource(R.drawable.ic_banner_point_normal);
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
        if (mOnBannerItemClickListener != null && mBannerList != null) {
            mOnBannerItemClickListener.onBannerItemClick(mBannerList.get(mCurrentIndex).getId());
        }
    }
}
