package com.chad.hlife.mvp.presenter.juhe.joke;

import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.juhe.JokeModel;
import com.chad.hlife.mvp.view.juhe.IJokeView;

import io.reactivex.ObservableTransformer;

public class JokePresenter extends BasePresenter<IJokeView> implements IJokePresenter {

    public void getJokeInfo(ObservableTransformer transformer, String key){
        JokeModel.getInstance().getJokeInfo(transformer, key, this);
    }

    public void getMoreJokeInfo(ObservableTransformer transformer, String sort, String time, String key){
        JokeModel.getInstance().getMoreJokeInfo(transformer, sort, time, key, this);
    }

    @Override
    public void onJokeInfo(JokeInfo jokeInfo) {
        getView().onJokeInfo(jokeInfo);
    }

    @Override
    public void onMoreJokeInfo(JokeInfo jokeInfo) {
        getView().onMoreJokeInfo(jokeInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
