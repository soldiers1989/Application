package com.chad.learning.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private Unbinder mUnbinder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        // 奶油刀绑定
        mUnbinder = ButterKnife.bind(this);
        initViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            // 奶油刀解绑
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initData();
}
