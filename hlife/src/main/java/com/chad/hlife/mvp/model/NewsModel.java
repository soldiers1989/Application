package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.mvp.presenter.news.INewsPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class NewsModel {

    private static volatile NewsModel mNewsModel = null;

    public static NewsModel getInstance() {
        synchronized (NewsModel.class) {
            if (mNewsModel == null) {
                mNewsModel = new NewsModel();
            }
        }
        return mNewsModel;
    }

    private NewsModel() {
    }

    public void getNewsInfo(ObservableTransformer transformer, String type, String key,
                            INewsPresenter newsPresenter) {
        HLifeRetrofit.getNewsInfo(type, key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> newsPresenter.onNewsInfo((NewsInfo) o),
                        throwable -> newsPresenter.onError(throwable));
    }
}
