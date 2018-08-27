package com.chad.learning.dagger.mvp.model;

import com.chad.learning.mvp.callback.Callback;

/**
 * Model层请求数据等耗时操作
 */
public class Model {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAIL = 1;
    public static final int TYPE_ERROR = 2;

    private Callback mCallback = null;

    public Model(Callback callback) {
        mCallback = callback;
    }

    public void request(int type) {
        if (mCallback == null) {
            return;
        }
        switch (type) {
            case TYPE_SUCCESS:
                mCallback.onSuccess("Network Request succeeded");
                break;
            case TYPE_FAIL:
                mCallback.onFail("Network Request failed");
                break;
            case TYPE_ERROR:
                mCallback.onError();
                break;
                default:
                    break;
        }
        mCallback.onComplete();
    }
}
