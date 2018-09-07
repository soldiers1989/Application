package com.chad.zhihu.mvp.zhihu.presenter.home;

import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.model.home.HomeModel;
import com.chad.zhihu.mvp.zhihu.view.IHomeView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    private static final String TAG = HomePresenter.class.getSimpleName();

    public void getLatestHomeInfo(ObservableTransformer transformer) {
        LogUtil.d(TAG, "getLatestHomeInfo");
        HomeModel.getInstance().getLatestHomeInfo(transformer, this);
    }

    public void getMoreHomeInfo(ObservableTransformer transformer, String date) {
        LogUtil.d(TAG, "getLatestHomeInfo : date = "+ date);
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
    public void onError() {
        getView().onError();
    }
}
