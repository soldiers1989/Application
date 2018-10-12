package com.chad.hlife.mvp.view.zhihu;

import com.chad.hlife.entity.zhihu.SectionDetailsInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface ISectionsView extends IBaseView {

    void OnSectionsInfo(SectionsInfo sectionsInfo);

    void onSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo);

    void onBeforeSectionDetailsInfo(SectionDetailsInfo sectionDetailsInfo);
}
