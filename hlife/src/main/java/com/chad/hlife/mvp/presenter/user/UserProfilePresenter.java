package com.chad.hlife.mvp.presenter.user;

import com.chad.hlife.entity.mob.UserProfileInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.UserProfileModel;
import com.chad.hlife.mvp.view.IUserProfileView;

import io.reactivex.ObservableTransformer;

public class UserProfilePresenter extends BasePresenter<IUserProfileView> implements IUserProfilePresenter {

    public void putGender(ObservableTransformer transformer, String key, String token, String uid,
                          String item, String value) {
        UserProfileModel.getInstance().putGender(transformer, key, token, uid, item, value, this);
    }

    public void queryGender(ObservableTransformer transformer, String key, String token, String uid,
                            String item) {
        UserProfileModel.getInstance().queryGender(transformer, key, token, uid, item, this);
    }

    public void putBirthday(ObservableTransformer transformer, String key, String token, String uid,
                            String item, String value) {
        UserProfileModel.getInstance().putBirthday(transformer, key, token, uid, item, value, this);
    }

    public void queryBirthday(ObservableTransformer transformer, String key, String token, String uid,
                              String item) {
        UserProfileModel.getInstance().queryBirthday(transformer, key, token, uid, item, this);
    }

    public void putMobilePhone(ObservableTransformer transformer, String key, String token, String uid,
                               String item, String value) {
        UserProfileModel.getInstance().putMobilePhone(transformer, key, token, uid, item, value, this);
    }

    public void queryMobilePhone(ObservableTransformer transformer, String key, String token, String uid,
                                 String item) {
        UserProfileModel.getInstance().queryMobilePhone(transformer, key, token, uid, item, this);
    }

    @Override
    public void onPutGender(UserProfileInfo userProfileInfo) {
        getView().onPutGender(userProfileInfo);
    }

    @Override
    public void onQueryGender(UserProfileInfo userProfileInfo) {
        getView().onQueryGender(userProfileInfo);
    }

    @Override
    public void onPutBirthday(UserProfileInfo userProfileInfo) {
        getView().onPutBirthday(userProfileInfo);
    }

    @Override
    public void onQueryBirthday(UserProfileInfo userProfileInfo) {
        getView().onQueryBirthday(userProfileInfo);
    }

    @Override
    public void onPutMobilePhone(UserProfileInfo userProfileInfo) {
        getView().onPutMobilePhone(userProfileInfo);
    }

    @Override
    public void onQueryMobilePhone(UserProfileInfo userProfileInfo) {
        getView().onQueryMobilePhone(userProfileInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
