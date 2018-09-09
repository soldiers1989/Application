package com.chad.zhihu.mvp.zhihu.model.themes;

import com.chad.zhihu.entity.zhihu.HomeInfo;
import com.chad.zhihu.entity.zhihu.ThemeDetailsInfo;
import com.chad.zhihu.entity.zhihu.ThemesInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.zhihu.presenter.themes.IThemesPresenter;
import com.chad.zhihu.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableTransformer;

public class ThemesModel implements IThemesModel {

    private static final String TAG = ThemesModel.class.getSimpleName();

    private static volatile ThemesModel themesModel = null;

    public static ThemesModel getInstance() {
        synchronized (ThemesModel.class) {
            if (themesModel == null) {
                themesModel = new ThemesModel();
            }
        }
        return themesModel;
    }

    @Override
    public void getThemesInfo(ObservableTransformer transformer, IThemesPresenter presenter) {
        LogUtil.d(TAG, "getThemesInfo");
        ZhiHuRetrofitHelper.getThemesInfo()
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onThemesInfo((ThemesInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    @Override
    public void getThemeDetailsInfo(ObservableTransformer transformer, int id, IThemesPresenter presenter) {
        LogUtil.d(TAG, "getThemeDetailsInfo : id = " + id);
        ZhiHuRetrofitHelper.getThemeDetaildInfo(id)
                .compose(transformer)
                .map(o -> initStories((ThemeDetailsInfo) o))
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onThemeDetailsInfo((ThemeDetailsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    private ThemeDetailsInfo initStories(ThemeDetailsInfo detailsInfo) {
        LogUtil.d(TAG, "initStories : detailsInfo = " + detailsInfo);
        if (detailsInfo == null) {
            return null;
        }
        List<Integer> storyIds = new ArrayList<>();
        for (ThemeDetailsInfo.Story story : detailsInfo.getStories()) {
            storyIds.add(story.getId());
        }
        detailsInfo.setStoryIds(storyIds);
        return detailsInfo;
    }
}
