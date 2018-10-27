package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.mob.UserProfileInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IUserProfileView extends IBaseView {

    void onPutGender(UserProfileInfo userProfileInfo);

    void onQueryGender(UserProfileInfo userProfileInfo);

    void onPutBirthday(UserProfileInfo userProfileInfo);

    void onQueryBirthday(UserProfileInfo userProfileInfo);

    void onPutMobilePhone(UserProfileInfo userProfileInfo);

    void onQueryMobilePhone(UserProfileInfo userProfileInfo);
}
