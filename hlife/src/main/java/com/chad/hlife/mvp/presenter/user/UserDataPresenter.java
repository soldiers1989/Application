package com.chad.hlife.mvp.presenter.user;

import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.view.IUserDataView;

public class UserDataPresenter extends BasePresenter<IUserDataView> implements IUserDataPresenter {

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
