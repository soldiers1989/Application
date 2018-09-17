package com.chad.zhihu.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.zhihu.mvp.base.BasePresenter;

public abstract class BaseMvpRxAppCompatActivity<V, T extends BasePresenter<V>>
        extends BaseRxAppCompatActivity {

    protected T presenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = getPresenter();
        presenter.attachView((V) this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();
    }

    protected abstract T getPresenter();
}
