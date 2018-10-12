package com.chad.hlife.ui.juhe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.helper.ActivityHelper;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class BookContentAdapter extends BaseRecyclerViewAdapter<BookContentInfo.Data> {

    private Context mContext;

    public BookContentAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_books_store_content, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        BookContentInfo.Data bookData = data.get(position);
        CustomGlideModule.loadFitCenter(mContext, bookData.getImg(), itemViewHolder.imagePreview);
        itemViewHolder.textTitle.setText(bookData.getTitle());
        itemViewHolder.textCatalog.setText(bookData.getCatalog());
        itemViewHolder.textReading.setText(bookData.getReading());
        itemViewHolder.textTags.setText(bookData.getTags());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.text_title)
        AppCompatTextView textTitle;
        @BindView(R.id.text_catalog)
        AppCompatTextView textCatalog;
        @BindView(R.id.text_reading)
        AppCompatTextView textReading;
        @BindView(R.id.text_tags)
        AppCompatTextView textTags;
        @BindView(R.id.btn_buy)
        AppCompatButton btnBuy;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
