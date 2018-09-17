package com.chad.zhihu.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.zhihu.mvp.base.BasePresenter;

public abstract class BaseMvpRxFragment<V, T extends BasePresenter<V>> extends BaseRxFragment {

    protected T presenter = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = getPresenter();
        presenter.attachView((V) this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroyView();
    }

    protected abstract T getPresenter();
}
