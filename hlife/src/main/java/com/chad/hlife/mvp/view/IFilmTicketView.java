package com.chad.hlife.mvp.view;

import com.chad.hlife.entity.juhe.FilmTicketInfo;
import com.chad.hlife.mvp.base.IBaseView;

public interface IFilmTicketView extends IBaseView {

    void onFilmTicketInfo(FilmTicketInfo filmTicketInfo);
}