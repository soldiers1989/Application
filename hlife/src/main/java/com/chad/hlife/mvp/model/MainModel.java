package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.weibo.WeiBoUserInfo;
import com.chad.hlife.mvp.presenter.main.IMainPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class MainModel {

    private static volatile MainModel mMainModel = null;

    public static MainModel getInstance() {
        synchronized (MainModel.class) {
            if (mMainModel == null) {
                mMainModel = new MainModel();
            }
        }
        return mMainModel;
    }

    private MainModel() {
    }

    public void getWeiBoUserInfo(ObservableTransformer transformer, String accessToken, long uid,
                                 IMainPresenter mainPresenter) {
        HLifeRetrofit.getWeiBoUserInfo(accessToken, uid)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> mainPresenter.onWeiBoUserInfo((WeiBoUserInfo) o),
                        throwable -> mainPresenter.onError(throwable));
    }

    public void getWeiXinUserInfo(ObservableTransformer transformer, String accessToken,
                                  IMainPresenter mainPresenter) {

    }
}
