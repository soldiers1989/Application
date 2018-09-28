package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.QueryInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class QueryAdapter extends BaseRecyclerViewAdapter<QueryInfo> {

    private Context mContext;

    public QueryAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_query, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        QueryInfo queryInfo = data.get(position);
        itemViewHolder.imageQuery.setImageResource(queryInfo.getImageId());
        itemViewHolder.textQuery.setText(queryInfo.getTextId());
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.image_query)
        AppCompatImageView imageQuery;
        @BindView(R.id.text_query)
        AppCompatTextView textQuery;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
