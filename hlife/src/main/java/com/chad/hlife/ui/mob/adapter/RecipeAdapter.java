package com.chad.hlife.ui.mob.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class RecipeAdapter extends BaseRecyclerViewAdapter<RecipeDetailInfo.Recipes> {

    private Context mContext;

    public RecipeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recipe, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        RecipeDetailInfo.Recipes recipes = data.get(position);
        CustomGlideModule.loadCenterCrop(mContext, recipes.getThumbnail(), itemViewHolder.imagePreview);
        itemViewHolder.textName.setText(recipes.getName());
        itemViewHolder.textCtgTitle.setText(recipes.getCtgTitles());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.image_preview)
        AppCompatImageView imagePreview;
        @BindView(R.id.text_name)
        AppCompatTextView textName;
        @BindView(R.id.text_ctg_title)
        AppCompatTextView textCtgTitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
