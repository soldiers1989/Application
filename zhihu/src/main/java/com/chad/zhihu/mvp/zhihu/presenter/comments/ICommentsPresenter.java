package com.chad.zhihu.mvp.zhihu.presenter.comments;

import com.chad.zhihu.entity.CommentsInfo;
import com.chad.zhihu.mvp.base.IBasePresenter;

public interface ICommentsPresenter extends IBasePresenter {

    void onCommentsInfo(CommentsInfo commentsInfo);
}
