package com.chad.zhihu.mvp.zhihu.model.details;

import com.chad.zhihu.entity.zhihu.DetailsInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.hepler.retrofit.ZhiHuRetrofitHelper;
import com.chad.zhihu.mvp.zhihu.presenter.details.IDetailsPresenter;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class DetailsModel implements IDetailsModel {

    private static final String TAG = DetailsModel.class.getSimpleName();

    private static volatile DetailsModel detailsModel = null;

    public static DetailsModel getInstance() {
        synchronized (DetailsModel.class) {
            if (detailsModel == null) {
                detailsModel = new DetailsModel();
            }
        }
        return detailsModel;
    }

    private DetailsModel() {}

    @Override
    public void getDetailsInfo(ObservableTransformer transformer, int id, IDetailsPresenter presenter) {
        LogUtil.d(TAG, "getDetailsInfo : id = " + id);
        ZhiHuRetrofitHelper.getDetailsInfo(id)
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onDetailInfo((DetailsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }
}
