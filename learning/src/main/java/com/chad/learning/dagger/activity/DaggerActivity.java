package com.chad.learning.dagger.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.dagger.app.AppModule;
import com.chad.learning.dagger.app.DaggerAppComponent;
import com.chad.learning.dagger.mvp.Contract;
import com.chad.learning.dagger.mvp.presenter.Presenter;
import com.chad.learning.mvp.model.Model;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class DaggerActivity extends BaseAppCompatActivity implements View.OnClickListener, Contract.View {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;
    @BindView(R.id.btn_request_success)
    AppCompatButton mBtnRequestSuccess;
    @BindView(R.id.btn_request_fail)
    AppCompatButton mBtnRequestFail;
    @BindView(R.id.btn_request_error)
    AppCompatButton mBtnRequestError;

    // @Inject 带有此注解的属性或构造方法将参与到依赖注入中，Dagger2会实例化有此注解的类
    @Inject
    Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dagger;
    }

    @Override
    public void initViews() {
        mBtnRequestSuccess.setOnClickListener(this);
        mBtnRequestFail.setOnClickListener(this);
        mBtnRequestError.setOnClickListener(this);
    }

    @Override
    public void initData() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request_success:
                onRequestSuccessClick();
                break;
            case R.id.btn_request_fail:
                onRequestFailClick();
                break;
            case R.id.btn_request_error:
                onRequestErrorClick();
                break;
            default:
                break;
        }
    }

    private void onRequestSuccessClick() {
        if (mPresenter == null) {
            return;
        }
        mPresenter.reauestData(Model.TYPE_SUCCESS);
    }

    private void onRequestFailClick() {
        if (mPresenter == null) {
            return;
        }
        mPresenter.reauestData(Model.TYPE_FAIL);
    }

    private void onRequestErrorClick() {
        if (mPresenter == null) {
            return;
        }
        mPresenter.reauestData(Model.TYPE_ERROR);
    }

    @Override
    public void showLoading() {
        mTextContent.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        mTextContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess(String data) {
        mTextContent.setText(data);
    }

    @Override
    public void showFail(String data) {
        mTextContent.setText(data);
    }

    @Override
    public void showError() {
        mTextContent.setText("Error");
    }
}
