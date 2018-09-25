package com.chad.hlife.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseRxAppCompatActivity extends RxAppCompatActivity {

    private Unbinder unbinder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onGetLayoutId());
        unbinder = ButterKnife.bind(this);
        onInitView();
        onInitData();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        super.onDestroy();
    }

    protected abstract int onGetLayoutId();

    protected abstract void onInitView();

    protected abstract void onInitData();
}
