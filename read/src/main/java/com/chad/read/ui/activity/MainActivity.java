package com.chad.read.ui.activity;

import android.support.v7.widget.Toolbar;

import com.chad.read.R;
import com.chad.read.ui.base.BaseAppCompatActivity;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    @Override
    public void initData() {

    }
}
