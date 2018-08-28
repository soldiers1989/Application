package com.chad.learning.butterknife.activity;

import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ButterKnifeActivity extends BaseAppCompatActivity implements View.OnClickListener {

    // @BindView绑定控件
    @BindView(R.id.btn_test)
    AppCompatButton mBtnTest;

    @Override
    public int getLayoutId() {
        return R.layout.activity_butter_knife;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    // @OnClick声明点击事件
    @OnClick(R.id.btn_test)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                break;
            default:
                break;
        }
    }
}
