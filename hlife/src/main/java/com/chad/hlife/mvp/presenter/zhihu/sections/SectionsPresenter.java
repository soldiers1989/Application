package com.chad.hlife.mvp.presenter.zhihu.sections;

import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.SectionsModel;
import com.chad.hlife.mvp.view.zhihu.ISectionsView;

import io.reactivex.ObservableTransformer;

public class SectionsPresenter extends BasePresenter<ISectionsView> implements ISectionsPresenter {

    public void getSectionsInfo(ObservableTransformer transformer) {
        SectionsModel.getInstance().getSectionsInfo(transformer, this);
    }

    public void getSectionsDetailInfo(ObservableTransformer transformer, int id) {
        SectionsModel.getInstance().getSectionsDetailInfo(transformer, id, this);
    }

    public void getBeforeSectionsDetailInfo(ObservableTransformer transformer, int id,
                                            long timestamp) {
        SectionsModel.getInstance().getBeforeSectionsDetailInfo(transformer, id, timestamp, this);
    }

    @Override
    public void OnSectionsInfo(SectionsInfo sectionsInfo) {
        getView().OnSectionsInfo(sectionsInfo);
    }

    @Override
    public void onSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo) {
        getView().onSectionsDetailInfo(sectionsDetailInfo);
    }

    @Override
    public void onBeforeSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo) {
        getView().onBeforeSectionsDetailInfo(sectionsDetailInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
