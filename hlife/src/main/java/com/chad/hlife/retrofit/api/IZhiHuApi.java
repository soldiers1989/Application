package com.chad.hlife.retrofit.api;


import com.chad.hlife.entity.zhihu.CommentsInfo;
import com.chad.hlife.entity.zhihu.DetailExtraInfo;
import com.chad.hlife.entity.zhihu.DetailInfo;
import com.chad.hlife.entity.zhihu.HomeInfo;
import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.entity.zhihu.ThemesInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IZhiHuApi {

    /**
     * 获取最新的日报信息
     *
     * @return
     */
    @GET("stories/latest")
    Observable<HomeInfo> getLatestHomeInfo();

    /**
     * 根据日期获取对应的日报信息
     *
     * @return
     */
    @GET("stories/before/{date}")
    Observable<HomeInfo> getMoreHomeInfo(@Path("date") String date);

    /**
     * 获取专题列表
     *
     * @return
     */
    @GET("themes")
    Observable<ThemesInfo> getThemesInfo();

    /**
     * 根据ID获取对应的专题内容
     *
     * @param id
     * @return
     */
    @GET("theme/{id}")
    Observable<ThemesDetailInfo> getThemesDetailInfo(@Path("id") int id);

    /**
     * 获取专栏列表
     *
     * @return
     */
    @GET("sections")
    Observable<SectionsInfo> getSectionsInfo();

    /**
     * 根据ID获取对应的专栏内容
     *
     * @param id
     * @return
     */
    @GET("section/{id}")
    Observable<SectionsDetailInfo> getSectionsDetailInfo(@Path("id") int id);

    /**
     * 根据ID和Time获取对应的专栏之前内容
     *
     * @param id
     * @param timestamp
     * @return
     */
    @GET("section/{id}/before/{timestamp}")
    Observable<SectionsDetailInfo> getBeforeSectionsDetailInfo(@Path("id") int id,
                                                               @Path("timestamp") long timestamp);

    /**
     * 根据ID获取日报的详细信息
     *
     * @param id
     * @return
     */
    @GET("story/{id}")
    Observable<DetailInfo> getDetailInfo(@Path("id") int id);

    /**
     * 根据ID获取日报的额外信息
     *
     * @param id
     * @return
     */
    @GET("story-extra/{id}")
    Observable<DetailExtraInfo> getDetailExtraInfo(@Path("id") int id);

    /**
     * 根据ID获取日报的长评论
     *
     * @param id
     * @return
     */
    @GET("story/{id}/long-comments")
    Observable<CommentsInfo> getLongCommentsInfo(@Path("id") int id);

    /**
     * 根据ID获取日报的短评论
     *
     * @param id
     * @return
     */
    @GET("story/{id}/short-comments")
    Observable<CommentsInfo> getShortCommentsInfo(@Path("id") int id);
}
