package com.chad.weibo.mvp.presenter.main;

import com.chad.weibo.entity.User;
import com.chad.weibo.mvp.base.BasePresenter;
import com.chad.weibo.mvp.model.main.MainModel;
import com.chad.weibo.mvp.view.IMainView;
import com.chad.weibo.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    public void getUser(ObservableTransformer transformer, String access_token, long uid) {
        LogUtil.d(TAG, "getUser");
        MainModel.getInstance().getUser(transformer, access_token, uid, this);
    }

    @Override
    public void onUser(User user) {
        getView().onUser(user);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
