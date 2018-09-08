package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.zhihu.ThemesInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface IThemesView extends IBaseView {

    void onThemesInfo(ThemesInfo themesInfo);
}
