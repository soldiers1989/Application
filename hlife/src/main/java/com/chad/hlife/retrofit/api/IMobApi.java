package com.chad.hlife.retrofit.api;

import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMobApi {

    /**
     * 历史上的今天
     *
     * @return
     */
    @GET("appstore/history/query")
    Observable<HistoryInfo> getHistoryInfo(@Query("key") String key, @Query("day") String day);

    /**
     * 菜谱分类
     *
     * @param key
     * @return
     */
    @GET("v1/cook/category/query")
    Observable<RecipeCategoryInfo> getRecipeCategoryInfo(@Query("key") String key);

    /**
     * 根据末级分类标签ID获取菜谱详情
     *
     * @param key
     * @param cid
     * @return
     */
    @GET("v1/cook/menu/search")
    Observable<RecipeDetailInfo> getRecipeDetailInfoByCId(@Query("key") String key, @Query("cid") String cid,
                                                          @Query("page") int page, @Query("size") int size);

    /**
     * 根据菜谱名称获取菜谱详情
     *
     * @param key
     * @param name
     * @return
     */
    @GET("v1/cook/menu/search")
    Observable<RecipeDetailInfo> getRecipeDetailInfoByName(@Query("key") String key, @Query("name") String name,
                                                           @Query("page") int page, @Query("size") int size);
}
