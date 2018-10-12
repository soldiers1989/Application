package com.chad.hlife.ui.view.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private OnItemClickListener mOnItemClickListener = null;

    private List<AppCompatImageView> mImageViewList;

    private int mCurrentPosition = 0;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public BannerAdapter(List<AppCompatImageView> imageViewList) {
        mImageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (mImageViewList == null) {
            return null;
        }
        // 对ViewPager页号求模取出View列表中要显示的项
        position %= mImageViewList.size();
        if (position < 0) {
            position += mImageViewList.size();
        }
        mCurrentPosition = position;
        AppCompatImageView imageView = mImageViewList.get(position);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        // 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException
        ViewParent viewParent = imageView.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(imageView);
        }
        // 设置点击事件
        RxView.clicks(imageView)
                .subscribe(o -> {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mCurrentPosition);
                    }
                });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (listener == null) {
            return;
        }
        mOnItemClickListener = listener;
    }

    public void setImageViewList(List<AppCompatImageView> imageViewList) {
        mImageViewList = imageViewList;
        notifyDataSetChanged();
    }
}
