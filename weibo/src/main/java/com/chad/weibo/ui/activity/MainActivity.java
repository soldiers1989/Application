package com.chad.weibo.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.chad.weibo.R;
import com.chad.weibo.ui.base.BaseRxAppCompatActivity;
import com.chad.weibo.util.LogUtil;

import butterknife.BindView;

public class MainActivity extends BaseRxAppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.layout_drawer)
    DrawerLayout mLayoutDrawer;
    @BindView(R.id.layout_appbar)
    AppBarLayout mLayoutAppBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ActionBarDrawerToggle mActionBarDrawerToggle = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
        initToolbar();
        initDrawer();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_navigation);
        mToolbar.setTitle("全部");
        setSupportActionBar(mToolbar);
    }

    private void initDrawer() {
        LogUtil.d(TAG, "initDrawer");
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mLayoutDrawer, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mLayoutDrawer.addDrawerListener(mActionBarDrawerToggle);
    }
}
