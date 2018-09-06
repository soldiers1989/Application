package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.zhihu.DetailInfo;

public interface IDetailView {

    void onDetailInfo(DetailInfo detailInfo);

    void onFail();
}
