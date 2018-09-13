package com.chad.zhihu.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    protected List<T> data = null;

    private OnItemClickListener onItemClickListener = null;
    private OnItemLongClickListener onItemLongClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RxView.clicks(holder.itemView).subscribe(o -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
        RxView.longClicks(holder.itemView).subscribe(o -> {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
