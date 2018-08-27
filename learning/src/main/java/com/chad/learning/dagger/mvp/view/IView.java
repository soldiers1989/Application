package com.chad.learning.dagger.mvp.view;

/**
 * View层来通知Activity或Fragment刷新UI
 */
public interface IView {

    void showLoading();

    void hideLoading();

    void showSuccess(String data);

    void showFail(String data);

    void showError();
}
