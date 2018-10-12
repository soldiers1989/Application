package com.chad.hlife.mvp.presenter.zhihu.home;

import com.chad.hlife.entity.zhihu.HomeInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.HomeModel;
import com.chad.hlife.mvp.view.zhihu.IHomeView;

import io.reactivex.ObservableTransformer;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    public void getLatestHomeInfo(ObservableTransformer transformer) {
        HomeModel.getInstance().getLatestHomeInfo(transformer, this);
    }

    public void getMoreHomeInfo(ObservableTransformer transformer, String date) {
        HomeModel.getInstance().getMoreHomeInfo(transformer, date, this);
    }

    @Override
    public void onLatestHomeInfo(HomeInfo homeInfo) {
        getView().onLatestHomeInfo(homeInfo);
    }

    @Override
    public void onMoreHomeInfo(HomeInfo homeInfo) {
        getView().onMoreHomeInfo(homeInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
