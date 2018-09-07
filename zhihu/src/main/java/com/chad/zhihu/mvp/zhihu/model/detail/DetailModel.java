package com.chad.zhihu.mvp.zhihu.model.detail;

import com.chad.zhihu.entity.zhihu.DetailInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.zhihu.presenter.detail.IDetailPresenter;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class DetailModel implements IDetailModel {

    private static final String TAG = DetailModel.class.getSimpleName();

    private static volatile DetailModel detailModel = null;

    public static DetailModel getInstance() {
        synchronized (DetailModel.class) {
            if (detailModel == null) {
                detailModel = new DetailModel();
            }
        }
        return detailModel;
    }

    private DetailModel() {}

    @Override
    public void getDetailInfo(ObservableTransformer transformer, int id, IDetailPresenter presenter) {
        LogUtil.d(TAG, "getDetailInfo : id = " + id);
        ZhiHuRetrofitHelper.getDetailInfo(id)
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onDetailInfo((DetailInfo) o),
                        throwable -> presenter.onError());
    }
}
