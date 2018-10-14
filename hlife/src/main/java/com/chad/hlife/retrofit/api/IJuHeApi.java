package com.chad.hlife.retrofit.api;

import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.juhe.BookCatalogInfo;
import com.chad.hlife.entity.juhe.BookContentInfo;
import com.chad.hlife.entity.juhe.FilmTicketInfo;
import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.entity.juhe.NewsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IJuHeApi {

    /**
     * 新闻头条
     * @param type
     * @param key
     * @return
     */
    @GET("toutiao/index")
    Observable<NewsInfo> getNewsInfo(@Query("type") String type, @Query("key") String key);

    /**
     * 历史上的今天
     * @param key
     * @param date
     * @return
     */
    @GET("todayOnhistory/queryEvent.php")
    Observable<HistoryInfo> getHistoryInfo(@Query("key") String key, @Query("date") String date);

    /**
     * 历史上的今天详情
     * @param key
     * @param eId
     * @return
     */
    @GET("todayOnhistory/queryDetail.php")
    Observable<HistoryDetailInfo> getHistoryDetailInfo(@Query("key") String key, @Query("e_id") String eId);

    /**
     * 获取最新笑话
     * @param key
     * @return
     */
    @GET("joke/content/text.php")
    Observable<JokeInfo> getJokeInfo(@Query("key") String key);

    /**
     * 根据10为时间戳获取笑话
     * @param sort
     * @param time
     * @param key
     * @return
     */
    @GET("joke/content/list.php")
    Observable<JokeInfo> getMoreJokeInfo(@Query("sort") String sort, @Query("time") String time,
                                         @Query("key") String key);

    /**
     * 电影票
     * @param key
     * @return
     */
    @GET("wepiao/query")
    Observable<FilmTicketInfo> getFilmTicketInfo(@Query("key") String key);

    /**
     * 图书分类目录
     * @param key
     * @return
     */
    @GET(AppConstant.URL_BASE_JUHE_API + "goodbook/catalog")
    Observable<BookCatalogInfo> getBookCatalogInfo(@Query("key") String key);

    /**
     * 图书列表
     * @param key
     * @param catalogId
     * @param pn
     * @param rn
     * @return
     */
    @GET(AppConstant.URL_BASE_JUHE_API + "goodbook/query")
    Observable<BookContentInfo> getBookContentInfo(@Query("key") String key,
                                                   @Query("catalog_id") int catalogId,
                                                   @Query("pn") int pn, @Query("rn") int rn);
}
