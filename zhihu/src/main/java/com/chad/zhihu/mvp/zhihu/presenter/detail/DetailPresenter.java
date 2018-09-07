package com.chad.zhihu.mvp.zhihu.presenter.detail;

import com.chad.zhihu.entity.zhihu.DetailInfo;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.model.detail.DetailModel;
import com.chad.zhihu.mvp.zhihu.view.IDetailView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class DetailPresenter extends BasePresenter<IDetailView> implements IDetailPresenter {

    private static final String TAG = DetailPresenter.class.getSimpleName();

    public void getDetailInfo(ObservableTransformer transformer, int id) {
        LogUtil.d(TAG, "getDetailInfo : id = " + id);
        DetailModel.getInstance().getDetailInfo(transformer, id, this);
    }

    @Override
    public void onDetailInfo(DetailInfo detailInfo) {
        getView().onDetailInfo(detailInfo);
    }

    @Override
    public void onError() {
        getView().onError();
    }
}
