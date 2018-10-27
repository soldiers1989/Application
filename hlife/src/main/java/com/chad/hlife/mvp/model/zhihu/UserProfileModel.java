package com.chad.hlife.mvp.model.zhihu;

import com.chad.hlife.entity.mob.UserProfileInfo;
import com.chad.hlife.mvp.presenter.user.IUserProfilePresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class UserProfileModel {

    private static volatile UserProfileModel mUserProfileModel = null;

    public static UserProfileModel getInstance() {
        synchronized (UserProfileModel.class) {
            if (mUserProfileModel == null) {
                mUserProfileModel = new UserProfileModel();
            }
        }
        return mUserProfileModel;
    }

    private UserProfileModel() {
    }

    public void putGender(ObservableTransformer transformer, String key, String token, String uid,
                          String item, String value, IUserProfilePresenter userProfilePresenter) {
        HLifeRetrofit.putUserProfile(key, token, uid, item, value)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> userProfilePresenter.onPutGender((UserProfileInfo) o),
                        throwable -> userProfilePresenter.onError(throwable));
    }

    public void queryGender(ObservableTransformer transformer, String key, String token, String uid,
                            String item, IUserProfilePresenter userProfilePresenter) {
        HLifeRetrofit.queryUserProfile(key, token, uid, item)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> userProfilePresenter.onQueryGender((UserProfileInfo) o),
                        throwable -> userProfilePresenter.onError(throwable));
    }

    public void putBirthday(ObservableTransformer transformer, String key, String token, String uid,
                            String item, String value, IUserProfilePresenter userProfilePresenter) {
        HLifeRetrofit.putUserProfile(key, token, uid, item, value)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> userProfilePresenter.onPutBirthday((UserProfileInfo) o),
                        throwable -> userProfilePresenter.onError(throwable));
    }

    public void queryBirthday(ObservableTransformer transformer, String key, String token, String uid,
                              String item, IUserProfilePresenter userProfilePresenter) {
        HLifeRetrofit.queryUserProfile(key, token, uid, item)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> userProfilePresenter.onQueryBirthday((UserProfileInfo) o),
                        throwable -> userProfilePresenter.onError(throwable));
    }

    public void putMobilePhone(ObservableTransformer transformer, String key, String token, String uid,
                               String item, String value, IUserProfilePresenter userProfilePresenter) {
        HLifeRetrofit.putUserProfile(key, token, uid, item, value)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> userProfilePresenter.onPutMobilePhone((UserProfileInfo) o),
                        throwable -> userProfilePresenter.onError(throwable));
    }

    public void queryMobilePhone(ObservableTransformer transformer, String key, String token, String uid,
                                 String item, IUserProfilePresenter userProfilePresenter) {
        HLifeRetrofit.queryUserProfile(key, token, uid, item)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> userProfilePresenter.onQueryMobilePhone((UserProfileInfo) o),
                        throwable -> userProfilePresenter.onError(throwable));
    }
}
