package com.chad.learning.mvp.view;

/**
 * View层来通知Activity或Fragment刷新UI
 */
public interface View {

    void showLoading();

    void hideLoading();

    void showSuccess(String data);

    void showFail(String data);

    void showError();
}
