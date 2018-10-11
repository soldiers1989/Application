package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.juhe.WifiInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class WifiAdapter extends BaseRecyclerViewAdapter<WifiInfo.Data> {

    private Context mContext;

    public WifiAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_wifi, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        WifiInfo.Data wifiData = data.get(position);
        itemViewHolder.textName.setText(wifiData.getName());
        itemViewHolder.textPosition.setText(wifiData.getProvince().equals(wifiData.getCity()) ?
                wifiData.getCity() : wifiData.getProvince() + " " + wifiData.getCity());
        itemViewHolder.textAddress.setText(wifiData.getAddress());
        itemViewHolder.textDistance.setText(mContext.getString(R.string.current_distance)
                + wifiData.getDistance()
                + mContext.getString(R.string.meters));
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.text_name)
        AppCompatTextView textName;
        @BindView(R.id.text_position)
        AppCompatTextView textPosition;
        @BindView(R.id.text_address)
        AppCompatTextView textAddress;
        @BindView(R.id.text_distance)
        AppCompatTextView textDistance;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
