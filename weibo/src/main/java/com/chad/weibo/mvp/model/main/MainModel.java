package com.chad.weibo.mvp.model.main;

import com.chad.weibo.entity.User;
import com.chad.weibo.mvp.presenter.main.IMainPresenter;
import com.chad.weibo.retrofit.WeiBoRetrofit;
import com.chad.weibo.util.LogUtil;
import com.chad.weibo.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class MainModel implements IMainModel {

    private static final String TAG = MainModel.class.getSimpleName();

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

    @Override
    public void getUser(ObservableTransformer transformer, String access_token, long uid,
                        IMainPresenter mainPresenter) {
        LogUtil.d(TAG, "getUer");
        WeiBoRetrofit.getUser(access_token, uid)
                .compose(transformer)
                .compose(RxSchedulersUtil.thread())
                .subscribe(o -> mainPresenter.onUser((User) o),
                        throwable -> mainPresenter.onError(throwable));
    }
}
