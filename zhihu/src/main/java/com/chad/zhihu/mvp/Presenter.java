package com.chad.zhihu.mvp;

import com.chad.zhihu.entity.LatestDailyInfo;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import io.reactivex.ObservableTransformer;

public class Presenter implements Contract.IModel {

    private static final String TAG = Presenter.class.getSimpleName();

    private Contract.IView iView = null;
    private Model model = null;

    @Inject
    public Presenter(Contract.IView iView) {
        this.iView = iView;
        model = new Model(this);
    }

    private boolean isIViewExist() {
        if (iView == null) {
            return false;
        }
        return true;
    }

    private boolean isModelExist() {
        if (model == null) {
            return false;
        }
        return true;
    }

    public void getLatestDailyInfo(ObservableTransformer transformer) {
        if (!isModelExist()) {
            return;
        }
        model.getLatestDailyInfo(transformer);
    }

    @Override
    public void onLatestDailyInfo(LatestDailyInfo latestDailyInfo) {
        if (!isIViewExist()) {
            return;
        }
        iView.onLatestDailyInfo(latestDailyInfo);
    }

    @Override
    public void onFail() {
        if (!isIViewExist()) {
            return;
        }
        iView.onFail();
    }
}
