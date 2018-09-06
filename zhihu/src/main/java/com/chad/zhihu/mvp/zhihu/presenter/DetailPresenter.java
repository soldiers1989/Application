package com.chad.zhihu.mvp.zhihu.presenter;

import com.chad.zhihu.entity.zhihu.DetailInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.view.IDetailView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class DetailPresenter extends BasePresenter<IDetailView> {

    private static final String TAG = DetailPresenter.class.getSimpleName();

    public void getDetailInfo(ObservableTransformer transformer, int id) {
        LogUtil.d(TAG, "getDetailInfo : id = " + id);
        ZhiHuRetrofitHelper.getDetailInfo(id)
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> getView().onDetailInfo((DetailInfo) o),
                        throwable -> getView().onFail());
    }
}
