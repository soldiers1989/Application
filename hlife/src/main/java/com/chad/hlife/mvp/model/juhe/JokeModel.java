package com.chad.hlife.mvp.model.juhe;

import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.mvp.presenter.juhe.joke.IJokePresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class JokeModel {

    private static volatile JokeModel mJokeModel = null;

    public static JokeModel getInstance() {
        synchronized (JokeModel.class) {
            if (mJokeModel == null) {
                mJokeModel = new JokeModel();
            }
        }
        return mJokeModel;
    }

    private JokeModel() {
    }

    public void getJokeInfo(ObservableTransformer transformer, String key,
                            IJokePresenter jokePresenter) {
        HLifeRetrofit.getJokeInfo(key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> jokePresenter.onJokeInfo((JokeInfo) o),
                        throwable -> jokePresenter.onError(throwable));
    }

    public void getMoreJokeInfo(ObservableTransformer transformer, String sort, String time, String key,
                                IJokePresenter jokePresenter) {
        HLifeRetrofit.getMoreJokeInfo(sort, time, key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> jokePresenter.onMoreJokeInfo((JokeInfo) o),
                        throwable -> jokePresenter.onError(throwable));
    }
}
