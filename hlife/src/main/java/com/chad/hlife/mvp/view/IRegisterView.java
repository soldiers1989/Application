package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.mob.UserRegisterInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IRegisterView extends IBaseView {

    void onUserRegisterInfo(UserRegisterInfo userRegisterInfo);
}
