package com.chad.zhihu.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.zhihu.mvp.base.BasePresenter;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseRxFragment<V, T extends BasePresenter<V>> extends RxFragment {

    private Unbinder unbinder = null;

    protected T presenter = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        presenter = getPresenter();
        presenter.attachView((V) this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroyView();
    }

    protected abstract int getLayoutId();

    protected abstract T getPresenter();

    protected abstract void initViews();

    protected abstract void initData();
}
