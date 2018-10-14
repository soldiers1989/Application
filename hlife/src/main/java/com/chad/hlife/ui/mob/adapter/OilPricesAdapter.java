package com.chad.hlife.ui.mob.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.OilPricesInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class OilPricesAdapter extends BaseRecyclerViewAdapter<OilPricesInfo.Result> {

    private Context mContext;

    public OilPricesAdapter(Context context) {
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
        OilPricesInfo.Prices prices = data.get(position).getPrices();
        itemViewHolder.textProvince.setText(prices.getProvince());
        itemViewHolder.textDieselOil0.setText(mContext.getString(R.string.today_prices) + "："
                + prices.getDieselOil0());
        itemViewHolder.textGasoline90.setText(mContext.getString(R.string.today_prices) + "："
                + prices.getGasoline90());
        itemViewHolder.textGasoline93.setText(mContext.getString(R.string.today_prices) + "："
                + prices.getGasoline93());
        itemViewHolder.textGasoline97.setText(mContext.getString(R.string.today_prices) + "："
                + prices.getGasoline97());
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
