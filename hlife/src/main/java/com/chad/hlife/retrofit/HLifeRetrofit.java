package com.chad.hlife.retrofit;

import com.chad.hlife.HLifeApplication;
import com.chad.hlife.app.AppConstant;
import com.chad.hlife.entity.juhe.CardInfo;
import com.chad.hlife.entity.juhe.HistoryDetailInfo;
import com.chad.hlife.entity.juhe.HistoryInfo;
import com.chad.hlife.entity.juhe.JokeInfo;
import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.entity.juhe.PhonePlaceInfo;
import com.chad.hlife.entity.weibo.WeiBoUserInfo;
import com.chad.hlife.helper.NetworkHelper;
import com.chad.hlife.retrofit.api.IJuHeApi;
import com.chad.hlife.retrofit.api.IWeiBoApi;
import com.chad.hlife.util.LogUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HLifeRetrofit {

    private static final String TAG = HLifeRetrofit.class.getSimpleName();

    private static OkHttpClient mOkHttpClient = null;
    private static IWeiBoApi mIWeiBoApi = null;
    private static IJuHeApi mIJuHeApi = null;

    static {
        initOkHttpClient();
        initIJuHeApi();
    }

    public static Observable<WeiBoUserInfo> getWeiBoUserInfo(String accessToken, long uid) {
        initIWeiBoApi();
        return mIWeiBoApi.getUserInfo(accessToken, uid);
    }

    public static Observable<NewsInfo> getNewsInfo(String type, String key) {
        return mIJuHeApi.getNewsInfo(type, key);
    }

    public static Observable<HistoryInfo> getHistoryInfo(String key, String date) {
        return mIJuHeApi.getHistoryInfo(key, date);
    }

    public static Observable<HistoryDetailInfo> getHistoryDetailInfo(String key, String eId) {
        return mIJuHeApi.getHistoryDetailInfo(key, eId);
    }

    public static Observable<JokeInfo> getJokeInfo(String key) {
        return mIJuHeApi.getJokeInfo(key);
    }

    public static Observable<JokeInfo> getMoreJokeInfo(String sort, String time, String key) {
        return mIJuHeApi.getMoreJokeInfo(sort, time, key);
    }

    public static Observable<CardInfo> getCardInfo(String cardNo, String key) {
        return mIJuHeApi.getCardInfo(cardNo, key);
    }

    public static Observable<PhonePlaceInfo> getPhonePlaceInfo(int phone, String key) {
        return mIJuHeApi.getPhonePlaceInfo(phone, key);
    }

    private static void initIWeiBoApi() {
        LogUtil.d(TAG, "initIWeiBoApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.URL_BASE_WEIBO)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mIWeiBoApi = retrofit.create(IWeiBoApi.class);
    }

    private static void initIJuHeApi() {
        LogUtil.d(TAG, "initIJuHeApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.URL_BASE_JUHE)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mIJuHeApi = retrofit.create(IJuHeApi.class);
    }

    private static void initOkHttpClient() {
        LogUtil.d(TAG, "initOkHttpClient : mOkHttpClient = "
                + (mOkHttpClient == null ? null : "Not Null"));
        if (mOkHttpClient == null) {
            synchronized (HLifeRetrofit.class) {
                File cacheDir = HLifeApplication.getHLifeApplication().getCacheDir(); // 缓存文件目录
                File cacheFile = new File(cacheDir, "ZhiHuCache"); // 创建缓存文件
                int cacheMaxSize = 1024 * 1024 * 100; // 缓存大小为100M
                Cache cache = new Cache(cacheFile, cacheMaxSize); // 创建缓存文件
                // 将请求体打印出来，控制台可查看
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                mOkHttpClient = new OkHttpClient.Builder()
                        .cache(cache) // 配置缓存文件
                        .addInterceptor(httpLoggingInterceptor) // 添加Log拦截器
                        .addNetworkInterceptor(new HttpCacheInterceptor())  // 添加网络拦截器
                        .retryOnConnectionFailure(true) // 连接失败后重试
                        .connectTimeout(10, TimeUnit.SECONDS)   // 连接超时为10秒
                        .build();
            }
        }
    }

    /**
     * 自定义OkHttp缓存拦截器
     */
    private static class HttpCacheInterceptor implements Interceptor {

        private static final int MAX_AGE = 60 * 60; // 有网络时缓存最大失效时间为一小时
        private static final int MAX_STALE = 60 * 60 * 24; // 无网络时缓存最大失效时间为一天

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (NetworkHelper.isNetworkConnected()) {
                // 有网络时强制使用网络
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                // 无网络时强制使用缓存
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            // 将响应头添加至缓存
            Response response = chain.proceed(request);
            if (NetworkHelper.isNetworkConnected()) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + MAX_AGE)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale="
                                + MAX_STALE)
                        .build();
            }
            return response;
        }
    }
}
