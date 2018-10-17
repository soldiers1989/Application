package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.mvp.presenter.history.IHistoryPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class HistoryModel {

    private static volatile HistoryModel mHistoryModel = null;

    public static HistoryModel getInstance() {
        synchronized (HistoryModel.class) {
            if (mHistoryModel == null) {
                mHistoryModel = new HistoryModel();
            }
        }
        return mHistoryModel;
    }

    private HistoryModel() {
    }

    public void getHistoryInfo(ObservableTransformer transformer, String key, String day,
                               IHistoryPresenter historyPresenter) {
        HLifeRetrofit.getHistoryInfo(key, day)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> historyPresenter.onHistoryInfo((HistoryInfo) o),
                        throwable -> historyPresenter.onError(throwable));
    }
}
