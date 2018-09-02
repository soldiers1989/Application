package com.chad.zhihu.ui.view.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
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
       if (imageViewList == null) {
           return 0;
       }
       return imageViewList.size();
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
        currentPosition = position;
        AppCompatImageView imageView = imageViewList.get(position);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
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
        if (imageViewList == null) {
            return;
        }
        container.removeView(imageViewList.get(position));
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
