package com.chad.weibo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.chad.weibo.ui.base.BaseRecyclerAdapter;
import com.chad.weibo.ui.view.timeline.TimeLineView;

public class TimeLineAdapter extends BaseRecyclerAdapter<String> {

    private Context mContext;

    public TimeLineAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(new TimeLineView(mContext));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Status status = data.get(position);
//        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
//        TimeLineView timeLineView = (TimeLineView) itemViewHolder.itemView;
//        timeLineView.setUserAvatarUrl(status.getUser().getAvatar_large());
//        timeLineView.setUserName(status.getUser().getScreen_name());
//        timeLineView.setCreateAt(status.getCreated_at());
//        timeLineView.setSourcce(status.getSource());
//        timeLineView.setText(status.getText());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerAdapter.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
