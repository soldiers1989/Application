package com.chad.learning.dagger.mvp.callback;

public interface Callback {

    void onSuccess(String data);

    void onFail(String data);

    void onError();

    void onComplete();
}
