package com.chad.learning.mvp.presenter;

import android.os.Handler;
import android.os.Message;

import com.chad.learning.mvp.callback.Callback;
import com.chad.learning.mvp.model.Model;
import com.chad.learning.mvp.view.View;


public class Presenter implements Handler.Callback {

    private Handler mHandler = null;
    private Model mModel = null;
    private ModelCallback mModelCallback = null;
    private View mView = null;

    public Presenter(View view) {
        mHandler = new Handler(this);
        mView = view;
    }

    public void reauestData(int type) {
        if (mModel == null) {
            mModelCallback = new ModelCallback();
            mModel = new Model(mModelCallback);
        }
        if (mView != null) {
            mView.showLoading();
        }
        Message msg = mHandler.obtainMessage();
        msg.obj = type;
        mHandler.sendMessageDelayed(msg, 2000);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (mModel == null) {
            return false;
        }
        mModel.request((Integer) msg.obj);
        return false;
    }

    private class ModelCallback implements Callback {

        @Override
        public void onSuccess(String data) {
            if (mView != null) {
                mView.showSuccess(data);
            }
        }

        @Override
        public void onFail(String data) {
            if (mView != null) {
                mView.showFail(data);
            }
        }

        @Override
        public void onError() {
            if (mView != null) {
                mView.showError();
            }
        }

        @Override
        public void onComplete() {
            if (mView != null) {
                mView.hideLoading();
            }
        }
    }
}