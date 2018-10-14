package com.chad.hlife.mvp.presenter.juhe;

import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.juhe.NewsModel;
import com.chad.hlife.mvp.view.juhe.INewsView;

import io.reactivex.ObservableTransformer;

public class NewsPresenter extends BasePresenter<INewsView> implements INewsPresenter {

    public void getNewsInfo(ObservableTransformer transformer, String type, String key) {
        NewsModel.getInstance().getNewsInfo(transformer, type, key, this);
    }

    @Override
    public void onNewsInfo(NewsInfo newsInfo) {
        getView().onNewsInfo(newsInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
