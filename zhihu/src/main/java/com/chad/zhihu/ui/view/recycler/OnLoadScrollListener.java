package com.chad.zhihu.ui.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class OnLoadScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager linearLayoutManager = null;

    private int itemCount = 0;
    private int lastVisibleItemPosition = 0;
    private int currentPage = 1;

    private boolean isLoading = false;

    public OnLoadScrollListener() {}

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (linearLayoutManager == null) {
            return;
        }
        // 判断是否需要加载下一页
        itemCount = linearLayoutManager.getItemCount();
        lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (dy > 0 && !isLoading && (lastVisibleItemPosition > itemCount - 2)) {
            currentPage++;
            onLoad(currentPage);
            setLoading(true);
        }
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isLoading() {
        return isLoading;
    }

    protected abstract void onLoad(int currentPage);
}
