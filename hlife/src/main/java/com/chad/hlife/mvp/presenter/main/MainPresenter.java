package com.chad.hlife.mvp.presenter.main;

import com.chad.hlife.entity.weibo.WeiBoUserInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.MainModel;
import com.chad.hlife.mvp.view.IMainView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import io.reactivex.ObservableTransformer;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    public void getWeiBoUserInfo(ObservableTransformer transformer, String accessToken, long uid) {
        MainModel.getInstance().getWeiBoUserInfo(transformer, accessToken, uid, this);
    }

    public Oauth2AccessToken getOauth2AccessToken() {
        return MainModel.getInstance().getOauth2AccessToken();
    }

    @Override
    public void onWeiBoUserInfo(WeiBoUserInfo weiBoUserInfo) {
        getView().onWeiBoUserInfo(weiBoUserInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
