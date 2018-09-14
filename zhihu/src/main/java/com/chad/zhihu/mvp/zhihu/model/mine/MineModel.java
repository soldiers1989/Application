package com.chad.zhihu.mvp.zhihu.model.mine;

public class MineModel implements IMineModel {

    private static final String TAG = MineModel.class.getSimpleName();

    private static volatile MineModel mineModel;

    public static MineModel getInstance() {
        synchronized (MineModel.class) {
            if (mineModel == null) {
                mineModel = new MineModel();
            }
        }
        return mineModel;
    }

    private MineModel() {}
}
