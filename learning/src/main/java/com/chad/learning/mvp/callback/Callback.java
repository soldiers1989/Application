package com.chad.learning.mvp.callback;

/**
 * 用于Model请求数据回调给Presenter
 */
public interface Callback {

    void onSuccess(String data);

    void onFail(String data);

    void onError();

    void onComplete();
}
