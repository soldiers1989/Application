package com.chad.weibo.mvp.model;

import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.mvp.presenter.home.IHomePresenter;
import com.chad.weibo.retrofit.WeiBoRetrofit;
import com.chad.weibo.util.LogUtil;
import com.chad.weibo.util.RxSchedulersUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

public class HomeModel {

    private static final String TAG = HomeModel.class.getSimpleName();

    private static volatile HomeModel mHomeModel = null;

    public static HomeModel getInstance() {
        synchronized (HomeModel.class) {
            if (mHomeModel == null) {
                mHomeModel = new HomeModel();
            }
        }
        return mHomeModel;
    }

    public void getHomeTimeLine(ObservableTransformer transformer, String access_token, int count,
                                int page, int feature, IHomePresenter homePresenter) {
        LogUtil.d(TAG, "getHomeTimeLine : feature = " + feature);
        WeiBoRetrofit.getHomeTimeLine(access_token, count, page, feature)
                .compose(transformer)
                .compose(RxSchedulersUtil.thread())
                .subscribe(o -> homePresenter.onHomeTimeLine((TimeLine) o),
                        throwable -> homePresenter.onError(throwable));
    }

    public void getMoreHomeTimeLine(ObservableTransformer transformer, String access_token, int count,
                                int page, int feature, IHomePresenter homePresenter) {
        LogUtil.d(TAG, "getMoreHomeTimeLine : feature = " + feature);
        WeiBoRetrofit.getHomeTimeLine(access_token, count, page, feature)
                .compose(transformer)
                .compose(RxSchedulersUtil.thread())
                .subscribe(o -> homePresenter.onMoreHomeTimeLine((TimeLine) o),
                        throwable -> homePresenter.onError(throwable));
    }
}
