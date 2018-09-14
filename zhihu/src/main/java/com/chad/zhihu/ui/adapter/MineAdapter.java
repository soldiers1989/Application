package com.chad.zhihu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.zhihu.R;
import com.chad.zhihu.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class MineAdapter extends BaseRecyclerViewAdapter<String> {

    private Context context;

    private int[] iconIds = new int[]{R.drawable.ic_settings, R.drawable.ic_settings,
            R.drawable.ic_feedback, R.drawable.ic_settings};

    public MineAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_mine, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = data.get(position);
        if (TextUtils.isEmpty(title)) {
            return;
        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.imageTitle.setImageResource(iconIds[position]);
        itemViewHolder.textTitle.setText(title);
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.image_title)
        AppCompatImageView imageTitle;
        @BindView(R.id.text_title)
        AppCompatTextView textTitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
