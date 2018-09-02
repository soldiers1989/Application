package com.chad.zhihu.mvp;

public class Model {

    private static final String TAG = Model.class.getSimpleName();

    private Contract.IModel iModel = null;

    public Model(Contract.IModel iModel) {
        this.iModel = iModel;
    }
}
