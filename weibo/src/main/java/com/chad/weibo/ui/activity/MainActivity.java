package com.chad.weibo.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chad.weibo.R;
import com.chad.weibo.ui.base.BaseRxAppCompatActivity;
import com.chad.weibo.util.LogUtil;

import butterknife.BindView;

public class MainActivity extends BaseRxAppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.layout_drawer)
    DrawerLayout mLayoutDrawer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_navigation)
    NavigationView mNavigationView;

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
        initNavigation();
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
    }

    private void initToolbar() {
        LogUtil.d(TAG, "initToolbar");
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        // 设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDrawer() {
        LogUtil.d(TAG, "initDrawer");
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mLayoutDrawer, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState(); // 将Toolbar与Drawer同步
        mLayoutDrawer.addDrawerListener(mActionBarDrawerToggle);
    }

    private void initNavigation() {
        LogUtil.d(TAG, "initNavigation");
        MenuItem defaultMenuItem = mNavigationView.getMenu().getItem(0);
        setNavigationItemChecked(defaultMenuItem);

        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            // TODO: 2018/9/17 切换页面等逻辑
            setNavigationItemChecked(menuItem);
            return false;
        });
    }

    private void setNavigationItemChecked(MenuItem menuItem) {
        LogUtil.d(TAG, "setNavigationItemChecked : menuItem = " + menuItem);
        if (menuItem == null) {
            return;
        }
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        mToolbar.setTitle(menuItem.getTitle());
        mLayoutDrawer.closeDrawers();
    }
}
