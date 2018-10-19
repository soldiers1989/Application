package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;

public class CarBrandAdapter extends BaseRecyclerViewAdapter<CarBrandInfo.Result> {

    private static final int TYPE_ITEM_LETTER = 0;
    private static final int TYPE_ITEM_CONTENT = 1;

    private Context mContext;

    public CarBrandAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        switch (type) {
            case TYPE_ITEM_LETTER:
                View letterItemView = LayoutInflater.from(mContext).inflate(R.layout.item_car_brand_letter,
                        viewGroup, false);
                return new LetterItemViewHolder(letterItemView);
            case TYPE_ITEM_CONTENT:
                View contentItemView = LayoutInflater.from(mContext).inflate(R.layout.item_car_brand_content,
                        viewGroup, false);
                return new ContentItemViewHolder(contentItemView);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarBrandInfo.Result result = data.get(position);
        if (holder instanceof LetterItemViewHolder) {
            ((LetterItemViewHolder) holder).textLetter.setText(result.getFirstLetter());
        }
        ((ContentItemViewHolder) holder).textBrand.setText(result.getName());
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM_LETTER;
        }
        String lastLetter = data.get(position - 1).getFirstLetter();
        String currentLetter = data.get(position).getFirstLetter();
        return !lastLetter.equals(currentLetter) ? TYPE_ITEM_LETTER : TYPE_ITEM_CONTENT;
    }

    public class LetterItemViewHolder extends ContentItemViewHolder {

        @BindView(R.id.text_letter)
        AppCompatTextView textLetter;

        public LetterItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ContentItemViewHolder extends ViewHolder {

        @BindView(R.id.text_brand)
        AppCompatTextView textBrand;

        public ContentItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
