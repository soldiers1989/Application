package com.chad.learning.rxjava.operator.filter.activity;

import android.content.Intent;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.OnClick;

/**
 * 过滤操作符
 */
public class FilterOperatorActivity extends BaseAppCompatActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_filter;
    }

    @Override
    public void initViews() {

    }


    @Override
    public void initData() {

    }

    @OnClick(R.id.btn_conditions)
    public void onConditionsClick() {
        Intent intent = new Intent(this, ConditionsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_count)
    public void onCountClick() {
        Intent intent = new Intent(this, CountActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_time)
    public void onTimeClick() {
        Intent intent = new Intent(this, TimeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_index)
    public void onIndexClick() {
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
    }
}
