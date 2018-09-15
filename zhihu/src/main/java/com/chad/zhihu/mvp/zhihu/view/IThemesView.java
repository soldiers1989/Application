package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.ThemeDetailsInfo;
import com.chad.zhihu.entity.ThemesInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface IThemesView extends IBaseView {

    void onThemesInfo(ThemesInfo themesInfo);

    void onThemeDetailsInfo(ThemeDetailsInfo themeDetailsInfo);
}
