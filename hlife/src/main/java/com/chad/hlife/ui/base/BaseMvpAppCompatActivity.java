package com.chad.hlife.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.hlife.mvp.base.BasePresenter;

public abstract class BaseMvpAppCompatActivity<V, T extends BasePresenter<V>>
        extends BaseRxAppCompatActivity {

    protected T presenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = onGetPresenter();
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

    protected abstract T onGetPresenter();
}
