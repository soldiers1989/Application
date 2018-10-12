package com.chad.hlife.ui.juhe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class HistoryDetailAdapter extends BaseRecyclerViewAdapter<HistoryDetailInfo.PicUrl> {

    private Context mContext;

    public HistoryDetailAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_history_detail, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        HistoryDetailInfo.PicUrl picUrl = data.get(position);
        itemViewHolder.textTile.setText(picUrl.getPicTitle());
        CustomGlideModule.loadFitXY(mContext, picUrl.getUrl(), itemViewHolder.imageDescription);
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTile;
        @BindView(R.id.image_description)
        AppCompatImageView imageDescription;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
