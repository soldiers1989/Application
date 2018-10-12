package com.chad.hlife.ui.juhe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class JokeAdapter extends BaseRecyclerViewAdapter<JokeInfo.Data> {

    private Context mContext;

    public JokeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_joke, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        JokeInfo.Data jokeData = data.get(position);
        itemViewHolder.textContent.setText(jokeData.getContent());
        itemViewHolder.textUpdateTime.setText(jokeData.getUpdatetime());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_content)
        AppCompatTextView textContent;
        @BindView(R.id.text_update_time)
        AppCompatTextView textUpdateTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
