package com.chad.zhihu.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.zhihu.mvp.base.BasePresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public abstract class BaseMvpRxAppCompatActivity<V, T extends BasePresenter<V>>
        extends RxAppCompatActivity {

    private Unbinder unbinder = null;

    protected T presenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        presenter = getPresenter();
        presenter.attachView((V) this);
        initViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract T getPresenter();

    protected abstract void initViews();

    protected abstract void initData();
}
