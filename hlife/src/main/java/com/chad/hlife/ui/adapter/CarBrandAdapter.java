package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class CarBrandAdapter extends BaseRecyclerViewAdapter<CarBrandInfo.Result> {

    private Context mContext;

    public CarBrandAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_car_brand, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        CarBrandInfo.Result result = data.get(position);
        itemViewHolder.textBrand.setText(result.getName());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.text_brand)
        AppCompatTextView textBrand;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
