package com.chad.zhihu.mvp;

import javax.inject.Inject;

public class Presenter implements Contract.IModel {

    private static final String TAG = Presenter.class.getSimpleName();

    private Contract.IView iView = null;
    private Model model = null;

    @Inject
    public Presenter(Contract.IView iView) {
        this.iView = iView;
        model = new Model(this);
    }
}
