package com.chad.zhihu.mvp.zhihu.presenter.sections;

import com.chad.zhihu.entity.zhihu.SectionDetailsInfo;
import com.chad.zhihu.entity.zhihu.SectionsInfo;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.model.sections.SectionsModel;
import com.chad.zhihu.mvp.zhihu.view.ISectionsView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class SectionsPresenter extends BasePresenter<ISectionsView> implements ISectionsPresenter {

    private static final String TAG = SectionsPresenter.class.getSimpleName();

    public void getSectionsInfo(ObservableTransformer transformer) {
        LogUtil.d(TAG, "getSectionsInfo");
        SectionsModel.getInstance().getSectionsInfo(transformer, this);
    }

    public void getSectionDetailsInfo(ObservableTransformer transformer, int id) {
        LogUtil.d(TAG, "getSectionDetailsInfo : id = " + id);
        SectionsModel.getInstance().getSectionDetailsInfo(transformer, id, this);
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
    public void onError(String msg) {
        getView().onError(msg);
    }
}
