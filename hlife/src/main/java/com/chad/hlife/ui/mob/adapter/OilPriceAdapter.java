package com.chad.hlife.ui.mob.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.OilPriceInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class OilPriceAdapter extends BaseRecyclerViewAdapter<OilPriceInfo.Price> {

    private Context mContext;

    public OilPriceAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_oil_prices, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        OilPriceInfo.Price price = data.get(position);
        itemViewHolder.textProvince.setText(price.getProvince());
        itemViewHolder.textDieselOil0.setText(mContext.getString(R.string.today_prices) + "："
                + price.getDieselOil0());
        itemViewHolder.textGasoline90.setText(mContext.getString(R.string.today_prices) + "："
                + price.getGasoline90());
        itemViewHolder.textGasoline93.setText(mContext.getString(R.string.today_prices) + "："
                + price.getGasoline93());
        itemViewHolder.textGasoline97.setText(mContext.getString(R.string.today_prices) + "："
                + price.getGasoline97());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends ViewHolder {

        @BindView(R.id.text_province)
        AppCompatTextView textProvince;
        @BindView(R.id.text_dieselOil0)
        AppCompatTextView textDieselOil0;
        @BindView(R.id.text_gasoline90)
        AppCompatTextView textGasoline90;
        @BindView(R.id.text_gasoline93)
        AppCompatTextView textGasoline93;
        @BindView(R.id.text_gasoline97)
        AppCompatTextView textGasoline97;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
