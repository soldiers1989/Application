package com.chad.learning.statusbar.activity;

import android.graphics.Color;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.statusbar.util.SystemStatusBarUtil;

import butterknife.OnClick;

public class KitKatActivity extends BaseAppCompatActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_statusbar_kitkat;
    }

    @Override
    public void initViews() {
        SystemStatusBarUtil.setStatusBarColor(this, Color.GREEN);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.btn_unlock)
    public void onUnLockClick() {
        SystemStatusBarUtil.unlockStatusBar(this);
    }

    @OnClick(R.id.btn_lock)
    public void onLockClick() {
        SystemStatusBarUtil.lockStatusBar(this);
    }
}
