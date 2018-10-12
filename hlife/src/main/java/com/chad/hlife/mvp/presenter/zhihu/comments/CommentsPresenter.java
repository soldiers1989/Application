package com.chad.hlife.mvp.presenter.zhihu.comments;

import com.chad.hlife.entity.zhihu.CommentsInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.zhihu.CommentsModel;
import com.chad.hlife.mvp.view.zhihu.ICommentsView;

import io.reactivex.ObservableTransformer;

public class CommentsPresenter extends BasePresenter<ICommentsView> implements ICommentsPresenter {

    public void getLongCommentsInfo(ObservableTransformer transformer, int id) {
        CommentsModel.getInstance().getLongCommentsInfo(transformer, id, this);
    }

    public void getShortCommentsInfo(ObservableTransformer transformer, int id) {
        CommentsModel.getInstance().getShortCommentsInfo(transformer, id, this);
    }

    @Override
    public void onCommentsInfo(CommentsInfo commentsInfo) {
        getView().onCommentsInfo(commentsInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
