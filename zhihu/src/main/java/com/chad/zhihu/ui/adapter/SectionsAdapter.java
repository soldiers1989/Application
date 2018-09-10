package com.chad.zhihu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.SectionsInfo;
import com.chad.zhihu.glide.CustomGlideModule;
import com.chad.zhihu.ui.base.BaseRecyclerViewAdapter;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;

public class SectionsAdapter extends BaseRecyclerViewAdapter<SectionsInfo.Section> {

    private static final String TAG = SectionsAdapter.class.getSimpleName();

    private Context mContext;

    public SectionsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LogUtil.d(TAG, "onCreateViewHolder");
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_sections,
                viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogUtil.d(TAG, "onBindViewHolder : holder = " + holder + " , position = " + position);
        SectionsInfo.Section section = data.get(position);
        if (section == null) {
            return;
        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        CustomGlideModule.loadImage(mContext, section.getThumbnail(), itemViewHolder.imagePreview);
        itemViewHolder.textName.setText(section.getName());
        itemViewHolder.textDescription.setText(section.getDescription());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.text_name)
        AppCompatTextView textName;
        @BindView(R.id.text_description)
        AppCompatTextView textDescription;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
