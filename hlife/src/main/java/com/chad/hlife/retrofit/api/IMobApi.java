package com.chad.hlife.retrofit.api;

import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.CarDetailInfo;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.entity.mob.UserLoginInfo;
import com.chad.hlife.entity.mob.UserRegisterInfo;
import com.chad.hlife.entity.mob.UserPasswordInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMobApi {

    /**
     * 用户注册
     *
     * @param key
     * @param userName
     * @param password
     * @return
     */
    @GET("user/rigister")
    Observable<UserRegisterInfo> register(@Query("key") String key, @Query("username") String userName,
                                          @Query("password") String password);

    /**
     * 用户登录
     *
     * @param key
     * @param userName
     * @param password
     * @return
     */
    @GET("user/login")
    Observable<UserLoginInfo> login(@Query("key") String key, @Query("username") String userName,
                                    @Query("password") String password);

    /**
     * 用户密码修改
     *
     * @param key
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @GET("user/password/change")
    Observable<UserPasswordInfo> updatePassword(@Query("key") String key, @Query("username") String userName,
                                                @Query("oldPassword") String oldPassword, @Query("newPassword") String newPassword);

    /**
     * 历史上的今天
     *
     * @return
     */
    @GET("appstore/history/query")
    Observable<HistoryInfo> getHistoryInfo(@Query("key") String key, @Query("day") String day);

    /**
     * 所有汽车品牌
     *
     * @param key
     * @return
     */
    @GET("car/brand/query")
    Observable<CarBrandInfo> getCarBrandInfo(@Query("key") String key);

    /**
     * 某品牌全部车型
     *
     * @param key
     * @param name
     * @return
     */
    @GET("car/seriesname/query")
    Observable<CarTypeInfo> getCarTypeInfo(@Query("key") String key, @Query("name") String name);

    /**
     * 汽车详情
     *
     * @param key
     * @param carId
     * @return
     */
    @GET("car/series/query")
    Observable<CarDetailInfo> getCarDetailInfo(@Query("key") String key, @Query("cid") String carId);

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
