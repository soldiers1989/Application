package com.chad.zhihu.mvp.zhihu.model.sections;

import com.chad.zhihu.entity.zhihu.SectionsInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.zhihu.presenter.sections.SectionsPresenter;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class SectionsModel implements ISectionsModel {

    private static final String TAG = SectionsModel.class.getSimpleName();

    private static volatile SectionsModel sectionsModel = null;

    public static SectionsModel getInstance() {
        synchronized (SectionsModel.class) {
            if (sectionsModel == null) {
                sectionsModel = new SectionsModel();
            }
        }
        return sectionsModel;
    }

    @Override
    public void getSectionsInfo(ObservableTransformer transformer, SectionsPresenter presenter) {
        LogUtil.d(TAG, "getSectionsInfo");
        ZhiHuRetrofitHelper.getSectionsInfo()
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.OnSectionsInfo((SectionsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }
}
