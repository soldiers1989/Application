package com.chad.hlife.mvp.model.juhe;

import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.mvp.presenter.juhe.history.IHistoryPresenter;
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

    public void getHistoryInfo(ObservableTransformer transformer, String key, String date,
                               IHistoryPresenter historyPresenter) {
        HLifeRetrofit.getHistoryInfo(key, date)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> historyPresenter.onHistoryInfo((HistoryInfo) o),
                        throwable -> historyPresenter.onError(throwable));
    }

    public void getHistoryDetailInfo(ObservableTransformer transformer, String key, String eId,
                                     IHistoryPresenter historyPresenter) {
        HLifeRetrofit.getHistoryDetailInfo(key, eId)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> historyPresenter.onHistoryDetailInfo((HistoryDetailInfo) o),
                        throwable -> historyPresenter.onError(throwable));
    }
}
