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

public class RecipeMainCategoryAdapter extends BaseRecyclerViewAdapter<RecipeCategoryInfo.Child> {

    private Context mContext;

    public RecipeMainCategoryAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_category_main, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeCategoryInfo.Child child = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        String name = child.getCategoryInfo().getName();
        itemViewHolder.textCategory.setText(name.substring(1, 3) + name.substring(name.length() - 2, name.length()));
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
