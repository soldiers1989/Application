package com.chad.hlife.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.zhihu.ThemesInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class ThemesAdapter extends BaseRecyclerViewAdapter<ThemesInfo.Others> {

    private Context mContext;

    public ThemesAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_themes, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThemesInfo.Others others = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        CustomGlideModule.loadCenterCrop(mContext, others.getThumbnail(), itemViewHolder.imagePreview);
        itemViewHolder.textDescription.setText(others.getDescription());
        itemViewHolder.textName.setText(others.getName());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.text_description)
        AppCompatTextView textDescription;
        @BindView(R.id.text_name)
        AppCompatTextView textName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
