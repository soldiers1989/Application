package com.chad.hlife.mvp.model.zhihu;

import com.chad.hlife.entity.zhihu.CommentsInfo;
import com.chad.hlife.mvp.presenter.zhihu.comments.ICommentsPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class CommentsModel {

    private static volatile CommentsModel commentsModel = null;

    public static CommentsModel getInstance() {
        synchronized (CommentsModel.class) {
            if (commentsModel == null) {
                commentsModel = new CommentsModel();
            }
        }
        return commentsModel;
    }

    private CommentsModel() {
    }

    public void getLongCommentsInfo(ObservableTransformer transformer, int id, ICommentsPresenter commentsPresenter) {
        HLifeRetrofit.getLongCommentsInfo(id)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> commentsPresenter.onCommentsInfo((CommentsInfo) o),
                        throwable -> commentsPresenter.onError(throwable));
    }

    public void getShortCommentsInfo(ObservableTransformer transformer, int id, ICommentsPresenter commentsPresenter) {
        HLifeRetrofit.getShortCommentsInfo(id)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> commentsPresenter.onCommentsInfo((CommentsInfo) o),
                        throwable -> commentsPresenter.onError(throwable));
    }
}
