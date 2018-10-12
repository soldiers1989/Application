package com.chad.hlife.mvp.view.zhihu;

import com.chad.hlife.entity.zhihu.CommentsInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface ICommentsView extends IBaseView {

    void onCommentsInfo(CommentsInfo commentsInfo);
}
