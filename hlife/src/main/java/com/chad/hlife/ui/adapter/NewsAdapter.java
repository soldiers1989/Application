package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class NewsAdapter extends BaseRecyclerViewAdapter<Integer> {

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
        itemViewHolder.textTitle.setText("杨幂的发际线再也回不去了么？网友吐槽像半秃");
        CustomGlideModule.load(mContext, "http://03.imgmini.eastday.com/mobile/20170105/20170105110355_" +
                "806f4ed3fe71d04fa452783d6736a02b_1_mwpm_03200403.jpeg", itemViewHolder.imageOne);
        CustomGlideModule.load(mContext, "http://03.imgmini.eastday.com/mobile/20170105/20170105110355_" +
                "806f4ed3fe71d04fa452783d6736a02b_2_mwpm_03200403.jpeg", itemViewHolder.imageTwo);
        CustomGlideModule.load(mContext, "http://03.imgmini.eastday.com/mobile/20170105/20170105110355_" +
                "806f4ed3fe71d04fa452783d6736a02b_3_mwpm_03200403.jpeg", itemViewHolder.imageThree);
        itemViewHolder.textDate.setText("2018-09-26 16:00");
        itemViewHolder.textAuthorName.setText("腾讯娱乐");
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
