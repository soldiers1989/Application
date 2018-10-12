package com.chad.hlife.mvp.presenter.zhihu.sections;

import com.chad.hlife.entity.zhihu.SectionDetailsInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.SectionsModel;
import com.chad.hlife.mvp.view.zhihu.ISectionsView;

import io.reactivex.ObservableTransformer;

public class SectionsPresenter extends BasePresenter<ISectionsView> implements ISectionsPresenter {

    public void getSectionsInfo(ObservableTransformer transformer) {
        SectionsModel.getInstance().getSectionsInfo(transformer, this);
    }

    public void getSectionDetailsInfo(ObservableTransformer transformer, int id) {
        SectionsModel.getInstance().getSectionDetailsInfo(transformer, id, this);
    }

    public void getBeforeSectionDetailsInfo(ObservableTransformer transformer, int id,
                                            long timestamp) {
        SectionsModel.getInstance().getBeforeSectionDetailsInfo(transformer, id, timestamp, this);
    }

    @Override
    public void OnSectionsInfo(SectionsInfo sectionsInfo) {
        getView().OnSectionsInfo(sectionsInfo);
    }

    @Override
    public void onSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo) {
        getView().onSectionDetailsInfo(sectionDetailsInfo);
    }

    @Override
    public void onBeforeSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo) {
        getView().onBeforeSectionDetailsInfo(sectionDetailsInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
