package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class RecipeSubCategoryAdapter extends BaseRecyclerViewAdapter<RecipeCategoryInfo.ChildCategoryInfo> {

    private Context mContext;

    public RecipeSubCategoryAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_category_sub, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeCategoryInfo.ChildCategoryInfo childCategoryInfo = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.textCategory.setText(childCategoryInfo.getCategoryInfo().getName());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.text_category)
        AppCompatTextView textCategory;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
