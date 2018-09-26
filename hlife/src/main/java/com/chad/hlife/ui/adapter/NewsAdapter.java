package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

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

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
