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
import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.hepler.glide.GlideApp;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.DateUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.util.WeekUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ContentItemViewHolder> {

    private static final String TAG = HomeAdapter.class.getSimpleName();

    private static final int TYPE_ITEM_DATE = 0;
    private static final int TYPE_ITEM_CONTENT = 1;

    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private List<HomeInfo.Stories> mStoriesList = null;

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public HomeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ContentItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LogUtil.d(TAG, "onCreateViewHolder : type = " + type);
        switch (type) {
            case TYPE_ITEM_DATE:
                View dateItemView = LayoutInflater.from(mContext).inflate(R.layout.item_latest_date,
                        viewGroup, false);
                return new DateItemViewHolder(dateItemView);
            case TYPE_ITEM_CONTENT:
                View contentItemView = LayoutInflater.from(mContext).inflate(R.layout.item_latest_content,
                        viewGroup, false);
                return new ContentItemViewHolder(contentItemView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentItemViewHolder contentItemViewHolder, int position) {
        HomeInfo.Stories stories = mStoriesList.get(position);
        if (stories == null) {
            return;
        }
        LogUtil.d(TAG, "onBindViewHolder : position = " + position);
        if (contentItemViewHolder instanceof DateItemViewHolder) {
            String date;
            if (position == 0) {
                date = StringUtil.findStringById(mContext, R.string.item_latest_date_today);
            } else {
                date = DateUtil.formatDate(mContext, stories.getDate()) + "  "
                        + WeekUtil.formatWeek(mContext, stories.getDate());
            }
            LogUtil.d(TAG, "onBindViewHolder : date = " + date);
            ((DateItemViewHolder) contentItemViewHolder).textDate.setText(date);
        }
        setItemStories(contentItemViewHolder, stories);
    }

    private void setItemStories(ContentItemViewHolder itemViewHolder, HomeInfo.Stories stories) {
        LogUtil.d(TAG, "setItemStories : itemViewHolder = " + itemViewHolder
                + " , stories = " + stories);
        if (itemViewHolder == null || stories == null) {
            return;
        }
        // 设置Title
        itemViewHolder.textTitle.setText(stories.getTitle());
        // 加载图片
        List<String> images = stories.getImages();
        if (images != null && images.size() > 0) {
            GlideApp.with(mContext)
                    .load(images.get(0)) // 设置URL
                    .centerCrop()   // 设置scaleType
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // 缓存
                    .placeholder(R.drawable.pic_default_item) // 设置默认占位图
                    .into(itemViewHolder.imagePreview);
        }
        // 如果用户读过这条新闻，就改变颜色
        if (stories.isLoad()) {
            itemViewHolder.textTitle.setTextColor(ColorUtil.findRgbById(mContext, R.color.colorItemTextPressed));
        } else {
            itemViewHolder.textTitle.setTextColor(ColorUtil.findRgbById(mContext, R.color.colorItemTextNormal));
        }

        // 如果是多图，就将多图图片显示出来
        if (stories.isMultiPic()) {
            itemViewHolder.multiPic.setVisibility(View.VISIBLE);
        } else {
            itemViewHolder.multiPic.setVisibility(View.GONE);
        }

        itemViewHolder.itemView.setOnClickListener(v -> {
            if (!stories.isLoad()) {
                itemViewHolder.textTitle.setTextColor(ColorUtil.findRgbById(mContext, R.color.colorItemTextPressed));
                stories.setLoad(true);
            }
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(stories.getId());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        LogUtil.d(TAG, "getItemViewType : position = " + position);
        if (position == 0) {
            return TYPE_ITEM_DATE;
        }
        String lastDate = mStoriesList.get(position - 1).getDate();
        String currentDate = mStoriesList.get(position).getDate();
        LogUtil.d(TAG, "getItemViewType : lastDate = " + lastDate
                + " , currentDate = " + currentDate);
        return !lastDate.equals(currentDate) ? TYPE_ITEM_DATE : TYPE_ITEM_CONTENT;
    }

    @Override
    public int getItemCount() {
        return mStoriesList == null ? 0 : mStoriesList.size();
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        if (listener == null) {
            return;
        }
        mOnItemClickListener = listener;
    }

    public void setStoriesList(List<HomeInfo.Stories> storiesList) {
        mStoriesList = storiesList;
        notifyDataSetChanged();
    }

    public void addStoriesList(List<HomeInfo.Stories> storiesList) {
        if (mStoriesList == null) {
            mStoriesList = new ArrayList<>();
        }
        mStoriesList.addAll(storiesList);
        notifyDataSetChanged();
    }

    public class DateItemViewHolder extends ContentItemViewHolder {

        @BindView(R.id.text_date)
        AppCompatTextView textDate;

        public DateItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ContentItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.image_multi)
        AppCompatImageView multiPic;

        public ContentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
