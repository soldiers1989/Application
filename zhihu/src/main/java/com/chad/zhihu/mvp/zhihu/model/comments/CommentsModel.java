package com.chad.zhihu.mvp.zhihu.model.comments;

import com.chad.zhihu.entity.zhihu.CommentsInfo;
import com.chad.zhihu.hepler.RxSchedulersHelper;
import com.chad.zhihu.retrofit.ZhiHuRetrofit;
import com.chad.zhihu.mvp.zhihu.presenter.comments.ICommentsPresenter;
import com.chad.zhihu.util.LogUtil;

import io.reactivex.ObservableTransformer;

public class CommentsModel implements ICommentsModel {

    private static final String TAG = CommentsModel.class.getSimpleName();

    private static volatile CommentsModel commentsModel = null;

    public static CommentsModel getInstance() {
        synchronized (CommentsModel.class) {
            if (commentsModel == null) {
                commentsModel = new CommentsModel();
            }
        }
        return commentsModel;
    }

    @Override
    public void getLongCommentsInfo(ObservableTransformer transformer, int id, ICommentsPresenter presenter) {
        LogUtil.d(TAG, "getLongCommentsInfo : id = " + id);
        ZhiHuRetrofit.getLongCommentsInfo(id)
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onCommentsInfo((CommentsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }

    @Override
    public void getShortCommentsInfo(ObservableTransformer transformer, int id, ICommentsPresenter presenter) {
        LogUtil.d(TAG, "getShortCommentsInfo : id = " + id);
        ZhiHuRetrofit.getShortCommentsInfo(id)
                .compose(transformer)
                .compose(RxSchedulersHelper.bindToMainThread())
                .subscribe(o -> presenter.onCommentsInfo((CommentsInfo) o),
                        throwable -> presenter.onError(throwable.toString()));
    }
}
