package com.chad.hlife.mvp.presenter.zhihu.detail;

import com.chad.hlife.entity.zhihu.DetailExtraInfo;
import com.chad.hlife.entity.zhihu.DetailInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.DetailModel;
import com.chad.hlife.mvp.view.zhihu.IDetailView;

import io.reactivex.ObservableTransformer;

public class DetailPresenter extends BasePresenter<IDetailView> implements IDetailPresenter {

    private static final String TAG = DetailPresenter.class.getSimpleName();

    public void getDetailInfo(ObservableTransformer transformer, int id) {
        DetailModel.getInstance().getDetailInfo(transformer, id, this);
        DetailModel.getInstance().getDetailExtraInfo(transformer, id, this);
    }

    @Override
    public void onDetailInfo(DetailInfo detailInfo) {
        getView().onDetailInfo(detailInfo);
    }

    @Override
    public void onDetailExtraInfo(DetailExtraInfo detailExtraInfo) {
        getView().onDetailExtraInfo(detailExtraInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
