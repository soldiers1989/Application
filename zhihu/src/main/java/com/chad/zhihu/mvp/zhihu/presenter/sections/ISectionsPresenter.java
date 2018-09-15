package com.chad.zhihu.mvp.zhihu.presenter.sections;

import com.chad.zhihu.entity.SectionDetailsInfo;
import com.chad.zhihu.entity.SectionsInfo;
import com.chad.zhihu.mvp.base.IBasePresenter;

public interface ISectionsPresenter extends IBasePresenter {

    void OnSectionsInfo(SectionsInfo sectionsInfo);

    void onSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo);

    void onBeforeSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo);
}
