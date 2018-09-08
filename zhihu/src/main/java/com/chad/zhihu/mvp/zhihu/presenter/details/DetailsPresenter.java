package com.chad.zhihu.mvp.zhihu.presenter.details;

import com.chad.zhihu.entity.zhihu.DetailsInfo;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.model.details.DetailsModel;
import com.chad.zhihu.mvp.zhihu.view.IDetailsView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class DetailsPresenter extends BasePresenter<IDetailsView> implements IDetailsPresenter {

    private static final String TAG = DetailsPresenter.class.getSimpleName();

    public void getDetailsInfo(ObservableTransformer transformer, int id) {
        LogUtil.d(TAG, "getDetailsInfo : id = " + id);
        DetailsModel.getInstance().getDetailsInfo(transformer, id, this);
    }

    @Override
    public void onDetailInfo(DetailsInfo detailsInfo) {
        getView().onDetailsInfo(detailsInfo);
    }

    @Override
    public void onError(String msg) {
        getView().onError(msg);
    }
}
