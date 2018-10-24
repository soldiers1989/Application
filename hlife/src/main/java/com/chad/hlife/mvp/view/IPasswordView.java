package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.mob.UserPasswordInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IPasswordView extends IBaseView {

    void onUserPasswordInfo(UserPasswordInfo userPasswordInfo);
}
