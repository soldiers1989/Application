package com.chad.hlife.mvp.view.zhihu;

import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface ISectionsView extends IBaseView {

    void OnSectionsInfo(SectionsInfo sectionsInfo);

    void onSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo);

    void onBeforeSectionsDetailInfo(SectionsDetailInfo sectionsDetailInfo);
}
