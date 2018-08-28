package com.chad.learning.lambda.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;

public class LambdaActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;
    @BindView(R.id.btn_test)
    AppCompatButton mBtnLambda;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lambda;
    }

    @Override
    public void initViews() {
        // 语法二
        mBtnLambda.setOnClickListener(this::onClick);
        // 语法一
        mBtnLambda.setOnLongClickListener(v -> {
            mTextContent.setText("onLongClick");
            return true;
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        mTextContent.setText("onClick");
    }
}
