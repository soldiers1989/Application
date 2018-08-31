package com.chad.learning.rxjava.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.rxjava.entrylevel.activity.EntryLevelActivity;
import com.chad.learning.rxjava.networkpolling.activity.PollingActivity;
import com.chad.learning.rxjava.operator.creation.activity.CreationOperatorActivity;
import com.chad.learning.rxjava.operator.function.activity.FunctionOperatorActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RxJavaActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_entry_level)
    AppCompatButton mBtnEntryLevel;
    @BindView(R.id.btn_operator_creation)
    AppCompatButton mBtnCreationOperator;
    @BindView(R.id.btn_operator_function)
    AppCompatButton mBtnFunctionOperator;
    @BindView(R.id.btn_network_polling)
    AppCompatButton mBtnNetworkPolling;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_entry_level, R.id.btn_operator_creation, R.id.btn_operator_function,
            R.id.btn_network_polling})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_entry_level:
                onEntryLevelClick();
                break;
            case R.id.btn_operator_creation:
                onCreationOperatorClick();
                break;
            case R.id.btn_operator_function:
                onFunctionOperatorClick();
                break;
            case R.id.btn_network_polling:
                onNetworkPolling();
                break;
            default:
                break;
        }
    }

    private void onEntryLevelClick() {
        Intent intent = new Intent(RxJavaActivity.this, EntryLevelActivity.class);
        startActivity(intent);
    }

    private void onCreationOperatorClick() {
        Intent intent = new Intent(RxJavaActivity.this, CreationOperatorActivity.class);
        startActivity(intent);
    }

    private void onFunctionOperatorClick() {
        Intent intent = new Intent(RxJavaActivity.this, FunctionOperatorActivity.class);
        startActivity(intent);
    }

    private void onNetworkPolling() {
        Intent intent = new Intent(RxJavaActivity.this, PollingActivity.class);
        startActivity(intent);
    }
}
