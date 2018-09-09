package com.chad.zhihu.mvp.zhihu.model.comments;

import com.chad.zhihu.mvp.zhihu.presenter.comments.ICommentsPresenter;

import io.reactivex.ObservableTransformer;

public interface ICommentsModel {

    void getLongCommentsInfo(ObservableTransformer transformer, int id, ICommentsPresenter presenter);

    void getShortCommentsInfo(ObservableTransformer transformer, int id, ICommentsPresenter presenter);
}
