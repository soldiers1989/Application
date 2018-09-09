package com.chad.zhihu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.hepler.glide.CustomGlideModule;
import com.chad.zhihu.ui.base.BaseRecyclerViewAdapter;
import com.chad.zhihu.util.ColorUtil;
import com.chad.zhihu.util.DateUtil;
import com.chad.zhihu.util.LogUtil;
import com.chad.zhihu.util.StringUtil;
import com.chad.zhihu.util.WeekUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends BaseRecyclerViewAdapter<HomeInfo.Story> {

    private static final String TAG = HomeAdapter.class.getSimpleName();

    private static final int TYPE_ITEM_DATE = 0;
    private static final int TYPE_ITEM_CONTENT = 1;

    private Context mContext;

    public HomeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LogUtil.d(TAG, "onCreateViewHolder : type = " + type);
        switch (type) {
            case TYPE_ITEM_DATE:
                View dateItemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_date,
                        viewGroup, false);
                return new DateItemViewHolder(dateItemView);
            case TYPE_ITEM_CONTENT:
                View contentItemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_content,
                        viewGroup, false);
                return new ContentItemViewHolder(contentItemView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeInfo.Story story = data.get(position);
        if (story == null) {
            return;
        }
        LogUtil.d(TAG, "onBindViewHolder : position = " + position);
        if (holder instanceof DateItemViewHolder) {
            String date;
            if (position == 0) {
                date = StringUtil.findStringById(mContext, R.string.item_latest_date_today);
            } else {
                date = DateUtil.formatDate(mContext, story.getDate()) + "  "
                        + WeekUtil.formatWeek(mContext, story.getDate());
            }
            LogUtil.d(TAG, "onBindViewHolder : date = " + date);
            ((DateItemViewHolder) holder).textDate.setText(date);
        }
        setItemStories((ContentItemViewHolder) holder, story);
        super.onBindViewHolder(holder, position);
    }

    private void setItemStories(ContentItemViewHolder itemViewHolder, HomeInfo.Story stories) {
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
            CustomGlideModule.loadImage(mContext, images.get(0), itemViewHolder.imagePreview);
        }

        // 如果是多图，就将多图图片显示出来
        if (stories.isMultiPic()) {
            itemViewHolder.multiPic.setVisibility(View.VISIBLE);
        } else {
            itemViewHolder.multiPic.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        LogUtil.d(TAG, "getItemViewType : position = " + position);
        if (position == 0) {
            return TYPE_ITEM_DATE;
        }
        String lastDate = data.get(position - 1).getDate();
        String currentDate = data.get(position).getDate();
        LogUtil.d(TAG, "getItemViewType : lastDate = " + lastDate
                + " , currentDate = " + currentDate);
        return !lastDate.equals(currentDate) ? TYPE_ITEM_DATE : TYPE_ITEM_CONTENT;
    }

    public class DateItemViewHolder extends ContentItemViewHolder {

        @BindView(R.id.text_date)
        AppCompatTextView textDate;

        public DateItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ContentItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.image_multi)
        AppCompatImageView multiPic;

        public ContentItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
