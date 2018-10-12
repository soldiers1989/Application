package com.chad.hlife.mvp.presenter.juhe.film;

import com.chad.hlife.entity.juhe.FilmTicketInfo;
import com.chad.hlife.mvp.base.BasePresenter;
import com.chad.hlife.mvp.model.juhe.FilmTicketModel;
import com.chad.hlife.mvp.view.juhe.IFilmTicketView;

import io.reactivex.ObservableTransformer;

public class FilmTicketPresenter extends BasePresenter<IFilmTicketView> implements IFilmTicketPresenter {

    public void getFilmTicketInfo(ObservableTransformer transformer, String key) {
        FilmTicketModel.getInstance().getFilmTicketInfo(transformer, key, this);
    }

    @Override
    public void onFilmTicketInfo(FilmTicketInfo filmTicketInfo) {
        getView().onFilmTicketInfo(filmTicketInfo);
    }

    @Override
    public void onError(Object object) {
        getView().onError(object);
    }
}
