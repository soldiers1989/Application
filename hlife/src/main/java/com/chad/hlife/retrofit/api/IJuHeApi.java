package com.chad.hlife.retrofit.api;

import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.entity.juhe.CardInfo;
import com.chad.hlife.entity.juhe.FilmTicketInfo;
import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.entity.juhe.PhonePlaceInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IJuHeApi {

    @GET("toutiao/index")
    Observable<NewsInfo> getNewsInfo(@Query("type") String type, @Query("key") String key);

    @GET("todayOnhistory/queryEvent.php")
    Observable<HistoryInfo> getHistoryInfo(@Query("key") String key, @Query("date") String date);

    @GET("todayOnhistory/queryDetail.php")
    Observable<HistoryDetailInfo> getHistoryDetailInfo(@Query("key") String key, @Query("e_id") String eId);

    @GET("joke/content/text.php")
    Observable<JokeInfo> getJokeInfo(@Query("key") String key);

    @GET("joke/content/list.php")
    Observable<JokeInfo> getMoreJokeInfo(@Query("sort") String sort, @Query("time") String time,
                                         @Query("key") String key);

    @GET("wepiao/query")
    Observable<FilmTicketInfo> getFilmTicketInfo(@Query("key") String key);

    @GET(AppConstant.URL_BASE_JUHE_API + "goodbook/catalog")
    Observable<BookCatalogInfo> getBookCatalogInfo(@Query("key") String key);

    @GET(AppConstant.URL_BASE_JUHE_API + "goodbook/query")
    Observable<BookContentInfo> getBookContentInfo(@Query("key") String key,
                                                   @Query("catalog_id") int catalogId,
                                                   @Query("pn") int pn, @Query("rn") int rn);

    @GET(AppConstant.URL_BASE_JUHE_API + "idcard/index")
    Observable<CardInfo> getCardInfo(@Query("cardno") String cardNo, @Query("key") String key);

    @GET(AppConstant.URL_BASE_JUHE_API + "mobile/get")
    Observable<PhonePlaceInfo> getPhonePlaceInfo(@Query("phone") int phone, @Query("key") String key);
}
