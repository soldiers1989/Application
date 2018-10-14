package com.chad.hlife.ui.juhe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class NewsAdapter extends BaseRecyclerViewAdapter<NewsInfo.Data> {

    private Context mContext;

    public NewsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_news, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        NewsInfo.Data newsData = data.get(position);
        itemViewHolder.textTitle.setText(newsData.getTitle());
        CustomGlideModule.loadCenterCrop(mContext, newsData.getThumbnailPicS(), itemViewHolder.imageOne);
        CustomGlideModule.loadCenterCrop(mContext, newsData.getThumbnailPicS02(), itemViewHolder.imageTwo);
        CustomGlideModule.loadCenterCrop(mContext, newsData.getThumbnailPicS03(), itemViewHolder.imageThree);
        itemViewHolder.textDate.setText(newsData.getDate());
        itemViewHolder.textAuthorName.setText(mContext.getString(R.string.source) + "："+ newsData.getAuthorName());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.image_one)
        AppCompatImageView imageOne;
        @BindView(R.id.image_two)
        AppCompatImageView imageTwo;
        @BindView(R.id.image_three)
        AppCompatImageView imageThree;
        @BindView(R.id.text_date)
        AppCompatTextView textDate;
        @BindView(R.id.text_author_name)
        AppCompatTextView textAuthorName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
