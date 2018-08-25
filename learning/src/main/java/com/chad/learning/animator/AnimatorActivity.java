package com.chad.learning.animator;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;

public class AnimatorActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_animator_object)
    AppCompatButton mBtnObject;
    @BindView(R.id.btn_animator_value)
    AppCompatButton mBtnValue;
    @BindView(R.id.btn_animator_set)
    AppCompatButton mBtnSet;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animator;
    }

    @Override
    public void initViews() {
        mBtnObject.setOnClickListener(this);
        mBtnValue.setOnClickListener(this);
        mBtnSet.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_animator_object:
                onAnimatorObjectClick();
                break;
            case R.id.btn_animator_value:
                onAnimatorValueClick();
                break;
            case R.id.btn_animator_set:
                onAnimatorSetClick();
                break;
                default:
                    break;
        }
    }

    private void onAnimatorObjectClick() {
        Intent intent = new Intent(AnimatorActivity.this, ObjectAnimatorActivity.class);
        startActivity(intent);
    }

    private void onAnimatorValueClick() {
        Intent intent = new Intent(AnimatorActivity.this, ValueAnimatorActivity.class);
        startActivity(intent);
    }

    private void onAnimatorSetClick() {
        Intent intent = new Intent(AnimatorActivity.this, AnimatorSetActivity.class);
        startActivity(intent);
    }
}
