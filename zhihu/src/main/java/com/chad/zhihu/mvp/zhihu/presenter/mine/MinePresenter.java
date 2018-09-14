package com.chad.zhihu.mvp.zhihu.presenter.mine;

import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.view.IMineView;

public class MinePresenter extends BasePresenter<IMineView> implements IMinePresenter {

    private static final String TAG = MinePresenter.class.getSimpleName();

    @Override
    public void onError(String msg) {
        getView().onError(msg);
    }
}
