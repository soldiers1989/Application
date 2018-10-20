package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class SettingsAdapter extends BaseRecyclerViewAdapter<String> {

    private static final int TYPE_LOGOUT = 0;
    private static final int TYPE_NORMAL = 1;

    private Context mContext;

    public SettingsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 3) {
            return TYPE_LOGOUT;
        }
        return TYPE_NORMAL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type == TYPE_LOGOUT) {
            View logoutItemView = LayoutInflater.from(mContext).inflate(R.layout.item_settings_logout, viewGroup, false);
            return new LogoutItemViewHolder(logoutItemView);
        } else if (type == TYPE_NORMAL) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_settings, viewGroup, false);
            return new ItemViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 3) {
            LogoutItemViewHolder logoutItemViewHolder = (LogoutItemViewHolder) holder;
            logoutItemViewHolder.textLogout.setText(data.get(position));
        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            String content = data.get(position);
            switch (position) {
                case 0:
                    itemViewHolder.textTitle.setText(R.string.developer);
                    break;
                case 1:
                    itemViewHolder.itemView.setBackgroundColor(Color.WHITE);
                    itemViewHolder.textTitle.setText(R.string.version);
                    break;
                case 2:
                    itemViewHolder.textTitle.setText(R.string.cache);
                    break;
                default:
                    break;
            }
            itemViewHolder.textContent.setText(content);
        }
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.text_content)
        AppCompatTextView textContent;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class LogoutItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_logout)
        AppCompatTextView textLogout;

        public LogoutItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
