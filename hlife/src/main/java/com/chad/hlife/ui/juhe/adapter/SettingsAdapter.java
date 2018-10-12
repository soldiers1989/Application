package com.chad.hlife.ui.juhe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class SettingsAdapter extends BaseRecyclerViewAdapter<String> {

    private Context mContext;

    public SettingsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_settings, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        String content = data.get(position);
        switch (position) {
            case 0:
                itemViewHolder.textTitle.setText(R.string.developer);
                break;
            case 1:
                itemViewHolder.textTitle.setText(R.string.version);
                break;
            case 2:
                itemViewHolder.textTitle.setText(R.string.cache);
                break;
            default:
                break;
        }
        itemViewHolder.textContent.setText(content);
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.text_content)
        AppCompatTextView textContent;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
