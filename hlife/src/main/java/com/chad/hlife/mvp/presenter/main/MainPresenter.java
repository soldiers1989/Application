package com.chad.hlife.mvp.presenter.main;

import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.weibo.WeiBoUserInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.MainModel;
import com.chad.hlife.mvp.view.IMainView;

import io.reactivex.ObservableTransformer;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    public void getUserInfo(int model, ObservableTransformer transformer, String accessToken, long uid) {
        switch (model) {
            case AppConstant.MODEL_LOGIN_WEIBO:
                MainModel.getInstance().getWeiBoUserInfo(transformer, accessToken, uid, this);
                break;
            case AppConstant.MODEL_LOGIN_WECHAT:
                MainModel.getInstance().getWeiXinUserInfo(transformer, accessToken, this);
                break;
            default:
                break;
        }
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
