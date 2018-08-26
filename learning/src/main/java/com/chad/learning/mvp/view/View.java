package com.chad.learning.mvp.view;

public interface View {

    void showLoading();

    void hideLoading();

    void showSuccess(String data);

    void showFail(String data);

    void showError();
}
