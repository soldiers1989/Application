package com.chad.zhihu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.ThemesInfo;
import com.chad.zhihu.hepler.glide.CustomGlideModule;
import com.chad.zhihu.ui.base.BaseRecyclerViewAdapter;
import com.chad.zhihu.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesAdapter extends BaseRecyclerViewAdapter<ThemesInfo.Others> {

    private static final String TAG = ThemesAdapter.class.getSimpleName();

    private Context mContext;

    public ThemesAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LogUtil.d(TAG, "onCreateViewHolder");
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_themes,
                viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogUtil.d(TAG, "onBindViewHolder : holder = " + holder + " , position = " + position);
        ThemesInfo.Others others = dataList.get(position);
        if (others == null) {
            return;
        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        CustomGlideModule.loadImage(mContext, others.getThumbnail(), itemViewHolder.imagePreview);
        itemViewHolder.textDescription.setText(others.getDescription());
        itemViewHolder.textName.setText(others.getName());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.text_description)
        AppCompatTextView textDescription;
        @BindView(R.id.text_name)
        AppCompatTextView textName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
