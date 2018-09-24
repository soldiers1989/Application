package com.chad.zhihu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.zhihu.R;
import com.chad.zhihu.app.AppSettings;
import com.chad.zhihu.entity.ThemeDetailsInfo;
import com.chad.zhihu.glide.CustomGlideModule;
import com.chad.zhihu.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class ThemeDetailsAdapter extends BaseRecyclerViewAdapter<ThemeDetailsInfo.Story> {

    private Context mContext;

    public ThemeDetailsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_theme_details,
                viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThemeDetailsInfo.Story story = data.get(position);
        if (story == null) {
            return;
        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.textTitle.setText(story.getTitle());
        if (story.getImages() != null && story.getImages().size() > 0
                && AppSettings.getInstance().isShowPicture()) {
            CustomGlideModule.loadImage(mContext, story.getImages().get(0), itemViewHolder.imagePreview);
        } else {
            itemViewHolder.imagePreview.setImageResource(R.drawable.pic_default_placeholder);
        }
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
