package com.chad.zhihu.mvp.zhihu.presenter.comments;

import com.chad.zhihu.entity.CommentsInfo;
import com.chad.zhihu.mvp.base.BasePresenter;
import com.chad.zhihu.mvp.zhihu.model.comments.CommentsModel;
import com.chad.zhihu.mvp.zhihu.view.ICommentsView;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class CommentsPresenter extends BasePresenter<ICommentsView> implements ICommentsPresenter {

    private static final String TAG = CommentsPresenter.class.getSimpleName();

    public void getLongCommentsInfo(ObservableTransformer transformer, int id) {
        LogUtil.d(TAG, "getLongCommentsInfo");
        CommentsModel.getInstance().getLongCommentsInfo(transformer, id, this);
    }

    public void getShortCommentsInfo(ObservableTransformer transformer, int id) {
        LogUtil.d(TAG, "getLongCommentsInfo");
        CommentsModel.getInstance().getShortCommentsInfo(transformer, id, this);
    }

    @Override
    public void onCommentsInfo(CommentsInfo commentsInfo) {
        getView().onCommentsInfo(commentsInfo);
    }

    @Override
    public void onError(String msg) {
        getView().onError(msg);
    }
}
