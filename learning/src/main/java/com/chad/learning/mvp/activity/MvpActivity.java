package com.chad.learning.mvp.activity;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.mvp.model.Model;
import com.chad.learning.mvp.presenter.Presenter;
import com.chad.learning.parent.base.BaseAppCompatActivity;

import butterknife.BindView;

public class MvpActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.text_content)
    AppCompatTextView mTextContent;
    @BindView(R.id.btn_request_success)
    AppCompatButton mBtnRequestSuccess;
    @BindView(R.id.btn_request_fail)
    AppCompatButton mBtnRequestFail;
    @BindView(R.id.btn_request_error)
    AppCompatButton mBtnRequestError;

    private Presenter mPresenter = null;
    private PresenterView mPresenterView = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    public void initViews() {
        mBtnRequestSuccess.setOnClickListener(this);
        mBtnRequestFail.setOnClickListener(this);
        mBtnRequestError.setOnClickListener(this);
    }

    @Override
    public void initData() {
        // 初始化Presenter
        mPresenterView = new PresenterView();
        mPresenter = new Presenter(mPresenterView);
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

    /**
     * View接口内部类，通过Presenter调用
     */
    private class PresenterView implements com.chad.learning.mvp.view.View {

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
}
