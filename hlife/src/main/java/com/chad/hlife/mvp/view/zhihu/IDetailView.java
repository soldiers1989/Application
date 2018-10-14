package com.chad.hlife.mvp.view.zhihu;

import com.chad.hlife.entity.zhihu.DetailExtraInfo;
import com.chad.hlife.entity.zhihu.DetailInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IDetailView extends IBaseView {

    void onDetailInfo(DetailInfo detailInfo);

    void onDetailExtraInfo(DetailExtraInfo detailExtraInfo);
}
