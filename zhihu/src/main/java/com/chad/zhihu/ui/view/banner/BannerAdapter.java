package com.chad.zhihu.ui.view.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private OnItemClickListener onItemClickListener = null;

    private List<AppCompatImageView> imageViewList;

    private int currentPosition = 0;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public BannerAdapter(List<AppCompatImageView> imageViewList) {
        this.imageViewList = imageViewList;
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
        if (imageViewList == null) {
            return null;
        }
        // 对ViewPager页号求模取出View列表中要显示的项
        position %= imageViewList.size();
        if (position < 0) {
            position += imageViewList.size();
        }
        currentPosition = position;
        AppCompatImageView imageView = imageViewList.get(position);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        // 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException
        ViewParent viewParent = imageView.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(imageView);
        }
        // 设置点击事件
        imageView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(currentPosition);
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
        onItemClickListener = listener;
    }

    public void setImageViewList(List<AppCompatImageView> imageViewList) {
        this.imageViewList = imageViewList;
        notifyDataSetChanged();
    }
}
