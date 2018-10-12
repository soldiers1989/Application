package com.chad.hlife.mvp.presenter.zhihu.details;

import com.chad.hlife.entity.zhihu.DetailsExtraInfo;
import com.chad.hlife.entity.zhihu.DetailsInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.DetailsModel;
import com.chad.hlife.mvp.view.zhihu.IDetailsView;

import io.reactivex.ObservableTransformer;

public class DetailsPresenter extends BasePresenter<IDetailsView> implements IDetailsPresenter {

    private static final String TAG = DetailsPresenter.class.getSimpleName();

    public void getDetailsInfo(ObservableTransformer transformer, int id) {
        DetailsModel.getInstance().getDetailsInfo(transformer, id, this);
        DetailsModel.getInstance().getDetailsExtraInfo(transformer, id, this);
    }

    @Override
    public void onDetailsInfo(DetailsInfo detailsInfo) {
        getView().onDetailsInfo(detailsInfo);
    }

    @Override
    public void onDetailsExtraInfo(DetailsExtraInfo detailsExtraInfo) {
        getView().onDetailsExtraInfo(detailsExtraInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
