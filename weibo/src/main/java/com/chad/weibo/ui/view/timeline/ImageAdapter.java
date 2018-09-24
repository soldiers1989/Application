package com.chad.weibo.ui.view.timeline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.weibo.R;
import com.chad.weibo.ui.base.BaseRecyclerAdapter;

public class ImageAdapter extends BaseRecyclerAdapter<String> {

    private Context mContext;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        return new ItemViewHolder(new AppCompatImageView(mContext));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        AppCompatImageView imageView = ((AppCompatImageView) itemViewHolder.itemView);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(R.drawable.pic_default_user_head);
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerAdapter.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
