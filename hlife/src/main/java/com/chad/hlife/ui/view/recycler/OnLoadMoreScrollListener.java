package com.chad.hlife.ui.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager = null;

    private int mItemCount = 0;
    private int mLastVisibleItemPosition = 0;

    private boolean isLoading = false;

    public OnLoadMoreScrollListener() {}

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mLinearLayoutManager == null) {
            return;
        }
        // 判断是否需要加载更多，如果最后还有两个Item，就开始加载更多
        mItemCount = mLinearLayoutManager.getItemCount();
        mLastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        if (dy > 0 && !isLoading && (mLastVisibleItemPosition > mItemCount - 2)) {
            onLoadMore();
            setLoading(true);
        }
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isLoading() {
        return isLoading;
    }

    protected abstract void onLoadMore();
}
