package com.chad.hlife.mvp.model;

import com.chad.hlife.entity.juhe.FilmTicketInfo;
import com.chad.hlife.mvp.presenter.film.IFilmTicketPresenter;
import com.chad.hlife.retrofit.HLifeRetrofit;
import com.chad.hlife.util.RxSchedulersUtil;

import io.reactivex.ObservableTransformer;

public class FilmTicketModel {

    private static volatile FilmTicketModel mFilmTicketModel = null;

    public static FilmTicketModel getInstance() {
        synchronized (FilmTicketModel.class) {
            if (mFilmTicketModel == null) {
                mFilmTicketModel = new FilmTicketModel();
            }
        }
        return mFilmTicketModel;
    }

    private FilmTicketModel() {
    }

    public void getFilmTicketInfo(ObservableTransformer transformer, String key,
                                  IFilmTicketPresenter filmTicketPresenter) {
        HLifeRetrofit.getFilmTicketInfo(key)
                .compose(transformer)
                .compose(RxSchedulersUtil.workThread())
                .subscribe(o -> filmTicketPresenter.onFilmTicketInfo((FilmTicketInfo) o),
                        throwable -> filmTicketPresenter.onError(throwable));
    }
}
