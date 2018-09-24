package com.chad.weibo.mvp.presenter.user;

import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.entity.User;
import com.chad.weibo.mvp.base.BasePresenter;
import com.chad.weibo.mvp.model.UserModel;
import com.chad.weibo.mvp.view.IUserView;
import com.chad.weibo.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class UserPresenter extends BasePresenter<IUserView> implements IUserPresenter {

    private static final String TAG = UserPresenter.class.getSimpleName();

    public void getUserTimeLine(ObservableTransformer transformer, String access_token, long uid,
                                int count, int page, int feature) {
        LogUtil.d(TAG, "getUserTimeLine");
        UserModel.getInstance().getUserTimeLine(transformer, access_token, uid, count, page, feature, this);
    }

    public void getMoreUserTimeLine(ObservableTransformer transformer, String access_token, long uid,
                                    int count, int page, int feature) {
        LogUtil.d(TAG, "getMoreUserTimeLine");
        UserModel.getInstance().getMoreUserTimeLine(transformer, access_token, uid, count, page, feature, this);
    }

    @Override
    public void onUserTimeLine(TimeLine timeLine) {
        getView().onUserTimeLine(timeLine);
    }

    @Override
    public void onMoreUserTimeLine(TimeLine timeLine) {
        getView().onMoreUserTimeLine(timeLine);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
