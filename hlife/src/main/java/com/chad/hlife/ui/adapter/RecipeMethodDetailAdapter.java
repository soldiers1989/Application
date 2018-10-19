package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class RecipeMethodDetailAdapter extends BaseRecyclerViewAdapter<RecipeDetailInfo.Method> {

    private Context mContext;

    public RecipeMethodDetailAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_detail_method, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeDetailInfo.Method method = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        if (TextUtils.isEmpty(method.getImg())) {
            itemViewHolder.mImagePreview.setVisibility(View.GONE);
        } else {
            itemViewHolder.mImagePreview.setVisibility(View.VISIBLE);
            CustomGlideModule.loadFitCenter(mContext, method.getImg(), itemViewHolder.mImagePreview);
        }
        itemViewHolder.textStep.setText(method.getStep());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.image_preview)
        AppCompatImageView mImagePreview;
        @BindView(R.id.text_step)
        AppCompatTextView textStep;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
