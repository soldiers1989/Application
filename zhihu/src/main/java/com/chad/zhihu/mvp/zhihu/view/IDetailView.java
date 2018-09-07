package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.zhihu.DetailInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface IDetailView extends IBaseView {

    void onDetailInfo(DetailInfo detailInfo);
}
