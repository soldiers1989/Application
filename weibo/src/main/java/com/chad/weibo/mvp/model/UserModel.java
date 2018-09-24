package com.chad.weibo.mvp.model;

import com.chad.weibo.entity.TimeLine;
import com.chad.weibo.mvp.presenter.user.IUserPresenter;
import com.chad.weibo.retrofit.WeiBoRetrofit;
import com.chad.weibo.util.LogUtil;
import com.chad.weibo.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class UserModel {

    private static final String TAG = UserModel.class.getSimpleName();

    private static volatile UserModel mUserModel = null;

    public static UserModel getInstance() {
        synchronized (UserModel.class) {
            if (mUserModel == null) {
                mUserModel = new UserModel();
            }
        }
        return mUserModel;
    }

    public void getUserTimeLine(ObservableTransformer transformer, String access_token, long uid,
                                int count, int page, int feature, IUserPresenter userPresenter) {
        LogUtil.d(TAG, "getUserTimeLine : feature = " + feature);
        WeiBoRetrofit.getUserTimeLine(access_token, uid, count, page, feature)
                .compose(transformer)
                .compose(RxSchedulersUtil.thread())
                .subscribe(o -> userPresenter.onUserTimeLine((TimeLine) o),
                        throwable -> userPresenter.onError(throwable));
    }

    public void getMoreUserTimeLine(ObservableTransformer transformer, String access_token, long uid,
                                    int count, int page, int feature, IUserPresenter userPresenter) {
        LogUtil.d(TAG, "getMoreUserTimeLine : feature = " + feature);
        WeiBoRetrofit.getUserTimeLine(access_token, uid, count, page, feature)
                .compose(transformer)
                .compose(RxSchedulersUtil.thread())
                .subscribe(o -> userPresenter.onMoreUserTimeLine((TimeLine) o),
                        throwable -> userPresenter.onError(throwable));
    }
}
