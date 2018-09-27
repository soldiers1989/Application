package com.chad.hlife.mvp.presenter.history;

import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.HistoryModel;
import com.chad.hlife.mvp.view.IHistoryView;

import io.reactivex.ObservableTransformer;

public class HistoryPresenter extends BasePresenter<IHistoryView> implements IHistoryPresenter {

    public void getHistoryInfo(ObservableTransformer transformer, String key, String date) {
        HistoryModel.getInstance().getHistoryInfo(transformer, key, date, this);
    }

    public void getHistoryDetailInfo(ObservableTransformer transformer, String key, String eId) {
        HistoryModel.getInstance().getHistoryDetailInfo(transformer, key, eId, this);
    }

    @Override
    public void onHistoryInfo(HistoryInfo historyInfo) {
        getView().onHistoryInfo(historyInfo);
    }

    @Override
    public void onHistoryDetailInfo(HistoryDetailInfo historyDetailInfo) {
        getView().onHistoryDetailInfo(historyDetailInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
