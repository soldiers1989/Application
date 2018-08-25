package com.chad.learning.parent.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.animator.AnimatorActivity;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.realm.activity.RealmActivity;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_animator)
    AppCompatButton mBtnAnimator;
    @BindView(R.id.btn_realm)
    AppCompatButton mBtnRealm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mBtnAnimator.setOnClickListener(this);
        mBtnRealm.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_animator:
                onAnimatorClick();
                break;
            case R.id.btn_realm:
                onRealmClick();
                break;
        }
    }

    private void onAnimatorClick() {
        Intent intent = new Intent(MainActivity.this, AnimatorActivity.class);
        startActivity(intent);
    }

    private void onRealmClick() {
        Intent intent = new Intent(MainActivity.this, RealmActivity.class);
        startActivity(intent);
    }
}
