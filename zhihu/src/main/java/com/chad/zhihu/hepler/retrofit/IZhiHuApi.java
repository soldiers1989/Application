package com.chad.zhihu.hepler.retrofit;

import com.chad.zhihu.entity.zhihu.LatestInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IZhiHuApi {

    /**
     * 获取最新的首页信息
     * @return
     */
    @GET("stories/latest")
    Observable<LatestInfo> getLatestInfo();
}
