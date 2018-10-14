package com.chad.hlife.ui.mob.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;
import com.chad.hlife.util.DateUtil;

import butterknife.BindView;

public class HistoryAdapter extends BaseRecyclerViewAdapter<HistoryInfo.Result> {

    private Context mContext;

    public HistoryAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_history, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        HistoryInfo.Result result = data.get(position);
        itemViewHolder.textTitle.setText(result.getTitle());
        itemViewHolder.textDate.setText(DateUtil.formatData(result.getDate()));
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.text_date)
        AppCompatTextView textDate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
