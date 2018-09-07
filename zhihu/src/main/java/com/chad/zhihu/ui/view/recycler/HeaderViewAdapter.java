package com.chad.zhihu.ui.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int START_HEADERS = Integer.MIN_VALUE;
    private static final int START_FOOTERS = Integer.MIN_VALUE + 10;
    private static final int START_ITEMS = Integer.MIN_VALUE + 20;
    private static final int TYPES_MAX_ADAPTER = 100;

    private RecyclerView.Adapter mWrappedAdapter = null;
    private HeaderAdapterDataObserver mHeaderAdapterDataObserver;

    private List<View> mHeaderViews;
    private List<View> mFooterViews;

    private Map<Class, Integer> mWrappedAdapterClass;

    public HeaderViewAdapter(RecyclerView.Adapter adapter) {
        mHeaderAdapterDataObserver = new HeaderAdapterDataObserver();
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
        mWrappedAdapterClass = new HashMap<>();
        setWrappedAdapter(adapter);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType < START_HEADERS + getHeaderItemCount()) {
            return new ViewHolder(mHeaderViews.get(viewType - START_HEADERS));
        } else if (viewType < START_FOOTERS + getFooterItemCount()) {
            return new ViewHolder(mFooterViews.get(viewType - START_FOOTERS));
        } else {
            return mWrappedAdapter.onCreateViewHolder(viewGroup, viewType - getWrappedAdapterOffset());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (mWrappedAdapter != null && position >= getHeaderItemCount()
                && position < getHeaderItemCount() + getWrappedAdapterItemCount()) {
            mWrappedAdapter.onBindViewHolder(viewHolder, position - getHeaderItemCount());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderItemCount()) {
            return START_HEADERS + position;
        } else if (position < getHeaderItemCount() + getWrappedAdapterItemCount()) {
            return getWrappedAdapterOffset() + mWrappedAdapter.getItemViewType(position -
                    getHeaderItemCount());
        } else {
            return START_FOOTERS + position - getHeaderItemCount() - getWrappedAdapterItemCount();
        }
    }

    @Override
    public int getItemCount() {
        return getWrappedAdapterItemCount() + getHeaderItemCount() + getFooterItemCount();
    }

    private void setWrappedAdapter(RecyclerView.Adapter adapter) {
        if (mWrappedAdapter != null) {
            mWrappedAdapter.unregisterAdapterDataObserver(mHeaderAdapterDataObserver);
        }
        mWrappedAdapter = adapter;
        Class adapterClass = mWrappedAdapter.getClass();
        if (mWrappedAdapterClass != null && !mWrappedAdapterClass.containsKey(adapterClass)) {
            putWrappedAdapterClass(adapterClass);
        }
        mWrappedAdapter.registerAdapterDataObserver(mHeaderAdapterDataObserver);
    }

    private void putWrappedAdapterClass(Class adapterClass) {
        if (mWrappedAdapterClass == null) {
            return;
        }
        mWrappedAdapterClass.put(adapterClass, START_ITEMS + mWrappedAdapterClass.size() * TYPES_MAX_ADAPTER);
    }

    private int getWrappedAdapterOffset() {
        if (mWrappedAdapterClass == null && mWrappedAdapter != null) {
            return 0;
        }
        return mWrappedAdapterClass.get(mWrappedAdapter.getClass());
    }

    public void addHeaderView(View headerView) {
        if (mHeaderViews == null) {
            return;
        }
        mHeaderViews.add(headerView);
    }

    public void addFooterView(View footerView) {
        if (mFooterViews == null) {

        }
        mFooterViews.add(footerView);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter == null) {
            return;
        }
        if (mWrappedAdapter != null && mWrappedAdapter.getItemCount() > 0) {
            notifyItemRangeRemoved(getHeaderItemCount(), mWrappedAdapter.getItemCount());
        }
        setWrappedAdapter(adapter);
        notifyItemRangeInserted(getHeaderItemCount(), mWrappedAdapter.getItemCount());
    }

    public int getWrappedAdapterItemCount() {
        return mWrappedAdapter == null ? 0 : mWrappedAdapter.getItemCount();
    }

    public int getHeaderItemCount() {
        return mHeaderViews == null ? 0 : mHeaderViews.size();
    }

    public int getFooterItemCount() {
        return mFooterViews == null ? 0 : mFooterViews.size();
    }

    private class HeaderAdapterDataObserver extends RecyclerView.AdapterDataObserver {

        @Override
        public void onChanged() {
            notifyDataSetChanged();
            super.onChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart + getHeaderItemCount(), itemCount);
            super.onItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart + getHeaderItemCount(), itemCount);
            super.onItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart + getHeaderItemCount(), itemCount);
            super.onItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            notifyItemRangeChanged(fromPosition + getHeaderItemCount(), toPosition +
                    itemCount + getHeaderItemCount());
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
