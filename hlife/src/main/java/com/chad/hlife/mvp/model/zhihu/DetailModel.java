package com.chad.hlife.mvp.model.zhihu;

import com.chad.hlife.entity.zhihu.DetailExtraInfo;
import com.chad.hlife.entity.zhihu.DetailInfo;
import com.chad.hlife.mvp.presenter.zhihu.detail.IDetailPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class DetailModel {

    private static volatile DetailModel detailModel = null;

    public static DetailModel getInstance() {
        synchronized (DetailModel.class) {
            if (detailModel == null) {
                detailModel = new DetailModel();
            }
        }
        return detailModel;
    }

    private DetailModel() {
    }

    public void getDetailInfo(ObservableTransformer transformer, int id, IDetailPresenter detailPresenter) {
        HLifeRetrofit.getDetailInfo(id)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> detailPresenter.onDetailInfo((DetailInfo) o),
                        throwable -> detailPresenter.onError(throwable));
    }

    public void getDetailExtraInfo(ObservableTransformer transformer, int id, IDetailPresenter detailPresenter) {
        HLifeRetrofit.getDetailExtraInfo(id)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> detailPresenter.onDetailExtraInfo((DetailExtraInfo) o),
                        throwable -> detailPresenter.onError(throwable));
    }
}
