package com.chad.hlife.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;
import com.chad.hlife.util.DateUtil;

import butterknife.BindView;

public class SectionsDetailAdapter extends BaseRecyclerViewAdapter<SectionsDetailInfo.Story> {

    private Context mContext;

    public SectionsDetailAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_sections_detail,
                viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SectionsDetailInfo.Story story = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.textTitle.setText(story.getTitle());
        itemViewHolder.textDate.setText(DateUtil.formatDate(mContext, story.getDate()));
        if (story.getImages() != null && story.getImages().size() > 0) {
            CustomGlideModule.loadCenterCrop(mContext, story.getImages().get(0), itemViewHolder.imagePreview);
        } else {
            itemViewHolder.imagePreview.setImageResource(R.drawable.pic_placeholder_image);
        }
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.text_date)
        AppCompatTextView textDate;
        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
