package com.chad.learning.parent.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.animator.AnimatorActivity;
import com.chad.learning.butterknife.activity.ButterKnifeActivity;
import com.chad.learning.dagger.activity.DaggerActivity;
import com.chad.learning.icepick.activity.IcepickActivity;
import com.chad.learning.lambda.activity.LambdaActivity;
import com.chad.learning.mvp.activity.MvpActivity;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.realm.activity.RealmActivity;
import com.chad.learning.retrofit.activity.RetrofitActivity;
import com.chad.learning.rxjava.activity.RxJavaActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_animator)
    AppCompatButton mBtnAnimator;
    @BindView(R.id.btn_realm)
    AppCompatButton mBtnRealm;
    @BindView(R.id.btn_mvp)
    AppCompatButton mBtnMvp;
    @BindView(R.id.btn_dagger)
    AppCompatButton mBtnDagger;
    @BindView(R.id.btn_lambda)
    AppCompatButton mBtnLambda;
    @BindView(R.id.btn_icepick)
    AppCompatButton mBtnIcepick;
    @BindView(R.id.btn_butter_knife)
    AppCompatButton mBtnButterKnife;
    @BindView(R.id.btn_retrofit)
    AppCompatButton mBtnRetrofit;
    @BindView(R.id.btn_rx_java)
    AppCompatButton mBtnRxJava;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_animator, R.id.btn_realm, R.id.btn_mvp, R.id.btn_dagger,
            R.id.btn_lambda, R.id.btn_icepick, R.id.btn_butter_knife, R.id.btn_retrofit,
            R.id.btn_rx_java})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_animator:
                onAnimatorClick();
                break;
            case R.id.btn_realm:
                onRealmClick();
                break;
            case R.id.btn_mvp:
                onMvpClick();
                break;
            case R.id.btn_dagger:
                onDaggerClick();
                break;
            case R.id.btn_lambda:
                onLambdaClick();
                break;
            case R.id.btn_icepick:
                onIcepickClick();
                break;
            case R.id.btn_butter_knife:
                onButterKnifeClick();
                break;
            case R.id.btn_retrofit:
                onRetrofitClick();
                break;
            case R.id.btn_rx_java:
                onRxJavaClick();
                break;
            default:
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

    private void onMvpClick() {
        Intent intent = new Intent(MainActivity.this, MvpActivity.class);
        startActivity(intent);
    }

    private void onDaggerClick() {
        Intent intent = new Intent(MainActivity.this, DaggerActivity.class);
        startActivity(intent);
    }

    private void onLambdaClick() {
        Intent intent = new Intent(MainActivity.this, LambdaActivity.class);
        startActivity(intent);
    }

    private void onIcepickClick() {
        Intent intent = new Intent(MainActivity.this, IcepickActivity.class);
        startActivity(intent);
    }

    private void onButterKnifeClick() {
        Intent intent = new Intent(MainActivity.this, ButterKnifeActivity.class);
        startActivity(intent);
    }

    private void onRetrofitClick() {
        Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
        startActivity(intent);
    }

    private void onRxJavaClick() {
        Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
        startActivity(intent);
    }

}
