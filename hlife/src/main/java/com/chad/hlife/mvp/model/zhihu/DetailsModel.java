package com.chad.hlife.mvp.model.zhihu;

import com.chad.hlife.entity.zhihu.DetailsExtraInfo;
import com.chad.hlife.entity.zhihu.DetailsInfo;
import com.chad.hlife.mvp.presenter.zhihu.details.IDetailsPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class DetailsModel {

    private static volatile DetailsModel detailsModel = null;

    public static DetailsModel getInstance() {
        synchronized (DetailsModel.class) {
            if (detailsModel == null) {
                detailsModel = new DetailsModel();
            }
        }
        return detailsModel;
    }

    private DetailsModel() {
    }

    public void getDetailsInfo(ObservableTransformer transformer, int id, IDetailsPresenter detailsPresenter) {
        HLifeRetrofit.getDetailsInfo(id)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> detailsPresenter.onDetailsInfo((DetailsInfo) o),
                        throwable -> detailsPresenter.onError(throwable));
    }

    public void getDetailsExtraInfo(ObservableTransformer transformer, int id, IDetailsPresenter detailsPresenter) {
        HLifeRetrofit.getDetailsExtraInfo(id)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> detailsPresenter.onDetailsExtraInfo((DetailsExtraInfo) o),
                        throwable -> detailsPresenter.onError(throwable));
    }
}
