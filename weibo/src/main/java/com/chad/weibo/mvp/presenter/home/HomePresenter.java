package com.chad.weibo.mvp.presenter.home;

import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.mvp.base.BasePresenter;
import com.chad.weibo.mvp.model.HomeModel;
import com.chad.weibo.mvp.view.IHomeView;
import com.chad.weibo.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    private static final String TAG = HomePresenter.class.getSimpleName();

    public void getHomeTimeLine(ObservableTransformer transformer, String access_token, int count,
                                int page, int feature) {
        LogUtil.d(TAG, "getHomeTimeLine");
        HomeModel.getInstance().getHomeTimeLine(transformer, access_token, count, page, feature, this);
    }

    public void getMoreHomeTimeLine(ObservableTransformer transformer, String access_token, int count,
                                int page, int feature) {
        LogUtil.d(TAG, "getMoreHomeTimeLine");
        HomeModel.getInstance().getHomeTimeLine(transformer, access_token, count, page, feature, this);
    }

    @Override
    public void onHomeTimeLine(TimeLine timeLine) {
        getView().onHomeTimeLine(timeLine);
    }

    @Override
    public void onMoreHomeTimeLine(TimeLine timeLine) {
        getView().onMoreHomeTimeLine(timeLine);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
