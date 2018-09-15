package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.SectionDetailsInfo;
import com.chad.zhihu.entity.SectionsInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface ISectionsView extends IBaseView {

    void OnSectionsInfo(SectionsInfo sectionsInfo);

    void onSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo);

    void onBeforeSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo);
}
