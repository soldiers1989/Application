package com.chad.hlife.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class SectionsAdapter extends BaseRecyclerViewAdapter<SectionsInfo.Section> {

    private Context mContext;

    public SectionsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_sections, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SectionsInfo.Section section = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        CustomGlideModule.loadCenterCrop(mContext, section.getThumbnail(), itemViewHolder.imagePreview);
        itemViewHolder.textName.setText(section.getName());
        itemViewHolder.textDescription.setText(section.getDescription());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.text_name)
        AppCompatTextView textName;
        @BindView(R.id.text_description)
        AppCompatTextView textDescription;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
