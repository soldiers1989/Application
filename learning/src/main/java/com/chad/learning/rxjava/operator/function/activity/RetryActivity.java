package com.chad.learning.rxjava.operator.function.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RetryActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_retry)
    AppCompatButton mBtnRetry;
    @BindView(R.id.btn_retry_long)
    AppCompatButton mBtnRetryLong;
    @BindView(R.id.btn_retry_predicate)
    AppCompatButton mBtnRetryPredicate;
    @BindView(R.id.btn_retry_bi_predicate)
    AppCompatButton mBtnRetryBiPredicate;
    @BindView(R.id.btn_retry_long_predicate)
    AppCompatButton mBtnRetryLongPredicate;
    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;

    private String mContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_java_operator_function_retry;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_retry, R.id.btn_retry_long, R.id.btn_retry_predicate,
            R.id.btn_retry_bi_predicate, R.id.btn_retry_long_predicate})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retry:
                onRetryClick();
                break;
            case R.id.btn_retry_long:
                onRetryLongClick();
                break;
            case R.id.btn_retry_predicate:
                onRetryPredicateClick();
                break;
            case R.id.btn_retry_bi_predicate:
                onRetryBiPredicateClick();
                break;
            case R.id.btn_retry_long_predicate:
                onRetryLongPredicateClick();
                break;
            default:
                break;
        }
    }

    private void onRetryClick() {

    }

    private void onRetryLongClick() {

    }

    private void onRetryPredicateClick() {

    }

    private void onRetryBiPredicateClick() {

    }

    private void onRetryLongPredicateClick() {

    }
}
