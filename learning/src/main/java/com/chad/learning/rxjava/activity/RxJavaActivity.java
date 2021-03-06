package com.chad.learning.rxjava.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.rxjava.demo.filter.search.SearchActivity;
import com.chad.learning.rxjava.demo.filter.shake.ShakeActivity;
import com.chad.learning.rxjava.demo.function.error.activity.ErrorRetryActivity;
import com.chad.learning.rxjava.demo.function.polling.activity.PollingActivity;
import com.chad.learning.rxjava.entrylevel.activity.EntryLevelActivity;

import com.chad.learning.rxjava.operator.condition.activity.ConditionOperatorActivity;
import com.chad.learning.rxjava.operator.creation.activity.CreationOperatorActivity;
import com.chad.learning.rxjava.operator.filter.activity.FilterOperatorActivity;
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
    @BindView(R.id.btn_operator_filter)
    AppCompatButton mBtnFilterOperator;
    @BindView(R.id.btn_network_polling)
    AppCompatButton mBtnNetworkPolling;
    @BindView(R.id.btn_network_error)
    AppCompatButton mBtnNeNetworkError;

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
            R.id.btn_operator_filter, R.id.btn_operator_condition, R.id.btn_network_polling,
            R.id.btn_network_error, R.id.btn_search_filter, R.id.btn_shake})
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
            case R.id.btn_operator_filter:
                onFilterOperatorClick();
                break;
            case R.id.btn_operator_condition:
                onConditionOperatorClick();
                break;
            case R.id.btn_network_polling:
                onNetworkPolling();
                break;
            case R.id.btn_network_error:
                onNetworkError();
                break;
            case R.id.btn_search_filter:
                onSearchFilterClick();
                break;
            case R.id.btn_shake:
                onShakeClick();
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

    private void onFilterOperatorClick() {
        Intent intent = new Intent(RxJavaActivity.this, FilterOperatorActivity.class);
        startActivity(intent);
    }

   private void onConditionOperatorClick() {
       Intent intent = new Intent(RxJavaActivity.this, ConditionOperatorActivity.class);
       startActivity(intent);
   }

    private void onNetworkPolling() {
        Intent intent = new Intent(RxJavaActivity.this, PollingActivity.class);
        startActivity(intent);
    }

    private void onNetworkError() {
        Intent intent = new Intent(RxJavaActivity.this, ErrorRetryActivity.class);
        startActivity(intent);
    }

    private void onSearchFilterClick() {
        Intent intent = new Intent(RxJavaActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    private void onShakeClick() {
        Intent intent = new Intent(RxJavaActivity.this, ShakeActivity.class);
        startActivity(intent);
    }
}
