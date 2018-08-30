package com.chad.learning.rxjava.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.rxjava.entrylevel.EntryLevelActivity;
import com.chad.learning.rxjava.operator.OperatorActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RxJavaActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_entry_level)
    AppCompatButton mBtnEntryLevel;
    @BindView(R.id.btn_operator)
    AppCompatButton mBtnOperator;

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

    @OnClick({R.id.btn_entry_level, R.id.btn_operator})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_entry_level:
                onEntryLevelClick();
                break;
            case R.id.btn_operator:
                onOperatorClick();
                break;
            default:
                break;
        }
    }

    private void onEntryLevelClick() {
        Intent intent =  new Intent(RxJavaActivity.this, EntryLevelActivity.class);
        startActivity(intent);
    }

    private void onOperatorClick() {
        Intent intent =  new Intent(RxJavaActivity.this, OperatorActivity.class);
        startActivity(intent);
    }
}
