package com.chad.zhihu.mvp.zhihu.view;

import com.chad.zhihu.entity.zhihu.CommentsInfo;
import com.chad.zhihu.mvp.base.IBaseView;

public interface ICommentsView extends IBaseView {

    void onCommentsInfo(CommentsInfo commentsInfo);
}
