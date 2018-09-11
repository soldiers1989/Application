package com.chad.learning.statusbar.activity;

import android.content.Intent;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.OnClick;

public class StatusBarActivity extends BaseAppCompatActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_statusbar;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.btn_kitkat)
    public void onKitKatClick() {
        Intent intent = new Intent(this, KitKatActivity.class);
        startActivity(intent);
    }
}
