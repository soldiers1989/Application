package com.chad.weibo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.weibo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSheetAdapter extends RecyclerView.Adapter {

    public static final int TYPE_LIST_ACCOUNT = 0;
    public static final int TYPE_LIST_PERSONAL = 1;

    private static final int TYPE_ITEM_TITLE = 0;
    private static final int TYPE_ITEM_DETAIL = 1;

    private Context mContext;

    private int mListType;

    private String[] mKeys;
    private String[] mValues;

    public UserSheetAdapter(Context context, int listType) {
        mContext = context;
        mListType = listType;
        switch (mListType) {
            case TYPE_LIST_ACCOUNT:
                mKeys = mContext.getResources().getStringArray(R.array.user_sheet_account);
                break;
            case TYPE_LIST_PERSONAL:
                mKeys = mContext.getResources().getStringArray(R.array.user_sheet_personal);
                break;
            default:
                break;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView;
        if (type == TYPE_ITEM_TITLE) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_user_sheet_title, viewGroup, false);
            return new TitleItemViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_user_sheet_detail, viewGroup, false);
            return new DetailItemViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof TitleItemViewHolder) {
            TitleItemViewHolder titleItemViewHolder = (TitleItemViewHolder) viewHolder;
            switch (mListType) {
                case TYPE_LIST_ACCOUNT:
                    titleItemViewHolder.title.setText(R.string.account_info);
                    break;
                case TYPE_LIST_PERSONAL:
                    titleItemViewHolder.title.setText(R.string.personal_info);
                    break;
                default:
                    break;
            }
        } else {
            DetailItemViewHolder detailItemViewHolder = (DetailItemViewHolder) viewHolder;
            detailItemViewHolder.key.setText(mKeys[position - 1]);
            if (mValues != null) {
                detailItemViewHolder.value.setText(mValues[position - 1]);
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM_TITLE;
        } else {
            return TYPE_ITEM_DETAIL;
        }
    }

    @Override
    public int getItemCount() {
        return mKeys == null ? 0 : mKeys.length + 1;
    }

    public void setValues(String[] values) {
        mValues = values;
        notifyDataSetChanged();
    }

    public class TitleItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        AppCompatTextView title;

        public TitleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class DetailItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.key)
        AppCompatTextView key;
        @BindView(R.id.value)
        AppCompatTextView value;

        public DetailItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
