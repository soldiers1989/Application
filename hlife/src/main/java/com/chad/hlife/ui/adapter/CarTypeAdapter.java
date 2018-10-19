package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.glide.CustomGlideModule;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class CarTypeAdapter extends BaseRecyclerViewAdapter<CarTypeInfo.Car> {

    private Context mContext;

    public CarTypeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_car_type, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarTypeInfo.Car car = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.textType.setText(mContext.getString(R.string.type) +"：" + car.getSeriesName());
        itemViewHolder.textPrice.setText(mContext.getString(R.string.guide_price) + "：" +car.getGuidePrice());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.text_type)
        AppCompatTextView textType;
        @BindView(R.id.text_price)
        AppCompatTextView textPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
