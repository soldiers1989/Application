package com.chad.hlife.retrofit;

import com.chad.hlife.app.AppConstant;
import com.chad.hlife.app.config.JuHeConfig;
import com.chad.hlife.app.config.MobConfig;
import com.chad.hlife.app.config.WeiBoConfig;
import com.chad.hlife.app.config.ZhiHuConfig;
import com.chad.hlife.entity.juhe.NewsInfo;
import com.chad.hlife.entity.mob.CarBrandInfo;
import com.chad.hlife.entity.mob.CarDetailInfo;
import com.chad.hlife.entity.mob.CarTypeInfo;
import com.chad.hlife.entity.mob.HistoryInfo;
import com.chad.hlife.entity.mob.RecipeCategoryInfo;
import com.chad.hlife.entity.mob.RecipeDetailInfo;
import com.chad.hlife.entity.mob.UserLoginInfo;
import com.chad.hlife.entity.mob.UserRegisterInfo;
import com.chad.hlife.entity.mob.UserPasswordInfo;
import com.chad.hlife.entity.weibo.WeiBoUserInfo;
import com.chad.hlife.entity.zhihu.CommentsInfo;
import com.chad.hlife.entity.zhihu.DetailExtraInfo;
import com.chad.hlife.entity.zhihu.DetailInfo;
import com.chad.hlife.entity.zhihu.HomeInfo;
import com.chad.hlife.entity.zhihu.SectionsDetailInfo;
import com.chad.hlife.entity.zhihu.SectionsInfo;
import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.entity.zhihu.ThemesInfo;
import com.chad.hlife.helper.NetworkHelper;
import com.chad.hlife.retrofit.api.IJuHeApi;
import com.chad.hlife.retrofit.api.IMobApi;
import com.chad.hlife.retrofit.api.IWeiBoApi;
import com.chad.hlife.retrofit.api.IZhiHuApi;
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
    private static IMobApi mIMobApi = null;
    private static IZhiHuApi mIZhiHuApi = null;

    static {
        initOkHttpClient();
        initIJuHeApi();
        initIMobApi();
        initIZhiHuApi();
    }

    public static Observable<WeiBoUserInfo> getWeiBoUserInfo(String accessToken, long uid) {
        initIWeiBoApi();
        return mIWeiBoApi.getUserInfo(accessToken, uid);
    }

    public static Observable<NewsInfo> getNewsInfo(String type, String key) {
        return mIJuHeApi.getNewsInfo(type, key);
    }

    public static Observable<UserRegisterInfo> register(String key, String userName, String password) {
        return mIMobApi.register(key, userName, password);
    }

    public static Observable<UserLoginInfo> login(String key, String userName, String password) {
        return mIMobApi.login(key, userName, password);
    }

    public static Observable<UserPasswordInfo> updatePassword(String key, String userName,
                                                              String oldPassword, String newPassword) {
        return mIMobApi.updatePassword(key, userName, oldPassword, newPassword);
    }

    public static Observable<HistoryInfo> getHistoryInfo(String key, String day) {
        return mIMobApi.getHistoryInfo(key, day);
    }

    public static Observable<CarBrandInfo> getCarBrandInfo(String key) {
        return mIMobApi.getCarBrandInfo(key);
    }

    public static Observable<CarTypeInfo> getCarTypeInfo(String key, String name) {
        return mIMobApi.getCarTypeInfo(key, name);
    }

    public static Observable<CarDetailInfo> getCarDetailInfo(String key, String carId) {
        return mIMobApi.getCarDetailInfo(key, carId);
    }

    public static Observable<RecipeCategoryInfo> getRecipeCategoryInfo(String key) {
        return mIMobApi.getRecipeCategoryInfo(key);
    }

    public static Observable<RecipeDetailInfo> getRecipeDetailInfoByCId(String key, String id,
                                                                        int page, int size) {
        return mIMobApi.getRecipeDetailInfoByCId(key, id, page, size);
    }

    public static Observable<RecipeDetailInfo> getRecipeDetailInfoByName(String key, String name,
                                                                         int page, int size) {
        return mIMobApi.getRecipeDetailInfoByName(key, name, page, size);
    }

    public static Observable<HomeInfo> getLatestHomeInfo() {
        return mIZhiHuApi.getLatestHomeInfo();
    }

    public static Observable<HomeInfo> getMoreHomeInfo(String date) {
        return mIZhiHuApi.getMoreHomeInfo(date);
    }

    public static Observable<ThemesInfo> getThemesInfo() {
        return mIZhiHuApi.getThemesInfo();
    }

    public static Observable<ThemesDetailInfo> getThemesDetailInfo(int id) {
        return mIZhiHuApi.getThemesDetailInfo(id);
    }

    public static Observable<SectionsInfo> getSectionsInfo() {
        return mIZhiHuApi.getSectionsInfo();
    }

    public static Observable<SectionsDetailInfo> getSectionsDetailInfo(int id) {
        return mIZhiHuApi.getSectionsDetailInfo(id);
    }

    public static Observable<SectionsDetailInfo> getBeforeSectionsDetailInfo(int id, long timestamp) {
        return mIZhiHuApi.getBeforeSectionsDetailInfo(id, timestamp);
    }

    public static Observable<DetailInfo> getDetailInfo(int id) {
        return mIZhiHuApi.getDetailInfo(id);
    }

    public static Observable<DetailExtraInfo> getDetailExtraInfo(int id) {
        return mIZhiHuApi.getDetailExtraInfo(id);
    }

    public static Observable<CommentsInfo> getLongCommentsInfo(int id) {
        return mIZhiHuApi.getLongCommentsInfo(id);
    }

    public static Observable<CommentsInfo> getShortCommentsInfo(int id) {
        return mIZhiHuApi.getShortCommentsInfo(id);
    }

    private static void initIWeiBoApi() {
        LogUtil.d(TAG, "initIWeiBoApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeiBoConfig.URL_BASE_WEIBO)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mIWeiBoApi = retrofit.create(IWeiBoApi.class);
    }

    private static void initIJuHeApi() {
        LogUtil.d(TAG, "initIJuHeApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JuHeConfig.URL_BASE_JUHE_V)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mIJuHeApi = retrofit.create(IJuHeApi.class);
    }

    private static void initIMobApi() {
        LogUtil.d(TAG, "initIMobApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MobConfig.URL_BASE_MOB)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mIMobApi = retrofit.create(IMobApi.class);
    }

    private static void initIZhiHuApi() {
        LogUtil.d(TAG, "initIZhiHuApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhiHuConfig.URL_BASE_ZHIHU)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mIZhiHuApi = retrofit.create(IZhiHuApi.class);
    }

    private static void initOkHttpClient() {
        LogUtil.d(TAG, "initOkHttpClient : mOkHttpClient = "
                + (mOkHttpClient == null ? null : "Not Null"));
        if (mOkHttpClient == null) {
            synchronized (HLifeRetrofit.class) {
                File cacheDir = AppConstant.FILE_DIR_CACHE; // 缓存文件目录
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
