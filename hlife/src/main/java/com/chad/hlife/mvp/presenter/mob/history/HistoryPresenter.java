package com.chad.hlife.mvp.presenter.mob.history;

import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.mob.HistoryModel;
import com.chad.hlife.mvp.view.mob.IHistoryView;

import io.reactivex.ObservableTransformer;

public class HistoryPresenter extends BasePresenter<IHistoryView> implements IHistoryPresenter {

    public void getHistoryInfo(ObservableTransformer transformer, String key, String day) {
        HistoryModel.getInstance().getHistoryInfo(transformer, key, day, this);
    }

    @Override
    public void onHistoryInfo(HistoryInfo historyInfo) {
        getView().onHistoryInfo(historyInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
