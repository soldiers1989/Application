package com.chad.zhihu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.LatestInfo;
import com.chad.zhihu.hepler.glide.GlideApp;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.DateUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.util.WeekUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ContentItemViewHolder> {

    private static final String TAG = LatestAdapter.class.getSimpleName();

    private static final int TYPE_ITEM_DATE = 0;
    private static final int TYPE_ITEM_CONTENT = 1;

    private Context context;
    private List<LatestInfo.Stories> storiesList = null;

    public LatestAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ContentItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        switch (type) {
            case TYPE_ITEM_DATE:
                View dateItemView = LayoutInflater.from(context).inflate(R.layout.item_latest_date,
                        viewGroup, false);
                return new DateItemViewHolder(dateItemView);
            case TYPE_ITEM_CONTENT:
                View contentItemView = LayoutInflater.from(context).inflate(R.layout.item_latest_content,
                        viewGroup, false);
                return new ContentItemViewHolder(contentItemView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentItemViewHolder contentItemViewHolder, int position) {

        LatestInfo.Stories stories = storiesList.get(position);
        if (stories == null) {
            return;
        }

        if (contentItemViewHolder instanceof DateItemViewHolder) {
            String date;
            if (position == 0) {
                date = StringUtil.findStringById(context, R.string.item_latest_date_today);
            } else {
                date = DateUtil.formatDate(context, stories.getDate()) + "  "
                        + WeekUtil.formatWeek(context, stories.getDate());
            }
            ((DateItemViewHolder) contentItemViewHolder).textDate.setText(date);
        }
        setItemStories(contentItemViewHolder, stories);
    }

    private void setItemStories(ContentItemViewHolder itemViewHolder, LatestInfo.Stories stories) {
        if (itemViewHolder == null || stories == null) {
            return;
        }
        itemViewHolder.textTitle.setText(stories.getTitle());
        List<String> images = stories.getImages();
        if (images != null && images.size() > 0) {
            GlideApp.with(context)
                    .load(images.get(0))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.pic_default_item)
                    .into(itemViewHolder.imageDisplay);
        }
        if (stories.isLoad()) {
            itemViewHolder.textTitle.setTextColor(ColorUtil.findRgbById(context, R.color.colorItemTextRead));
        } else {
            itemViewHolder.textTitle.setTextColor(ColorUtil.findRgbById(context, R.color.colorItemTextNormal));
        }
        if (stories.isMultiPic()) {
            itemViewHolder.multiPic.setVisibility(View.VISIBLE);
        } else {
            itemViewHolder.multiPic.setVisibility(View.GONE);
        }

        itemViewHolder.itemView.setOnClickListener(v -> {
            if (!stories.isLoad()) {
                itemViewHolder.textTitle.setTextColor(ColorUtil.findRgbById(context, R.color.colorItemTextRead));
                stories.setLoad(true);
            }
            // TODO: 2018/9/4 点击跳转
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM_DATE;
        }
        String lastDate = storiesList.get(position - 1).getDate();
        String currentDate = storiesList.get(position).getDate();
        return !lastDate.equals(currentDate) ? TYPE_ITEM_DATE : TYPE_ITEM_CONTENT;
    }

    @Override
    public int getItemCount() {
        return storiesList == null ? 0 : storiesList.size();
    }

    public void setStoriesList(List<LatestInfo.Stories> storiesList) {
        if (this.storiesList == null) {
            this.storiesList = new ArrayList<>();
        }
        this.storiesList.addAll(storiesList);
        notifyDataSetChanged();
    }

    public class DateItemViewHolder extends ContentItemViewHolder {

        @BindView(R.id.text_date)
        AppCompatTextView textDate;

        public DateItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ContentItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.image_display)
        AppCompatImageView imageDisplay;
        @BindView(R.id.image_multi)
        AppCompatImageView multiPic;

        public ContentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
