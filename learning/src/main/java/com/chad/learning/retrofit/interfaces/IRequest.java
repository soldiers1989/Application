package com.chad.learning.retrofit.interfaces;

import com.chad.learning.retrofit.entity.JSTranslation;
import com.chad.learning.retrofit.entity.YDTranslation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRequest {

    // 参数说明：
    // a：固定值 fy
    // f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
    // t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
    // w：查询内容

    /**
     * 指定Http请求方式为GET，传入baseURL以外的URL
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=你好世界")
    Call<JSTranslation> get();


    // 参数说明
    // doctype：json 或 xml
    // jsonversion：如果 doctype 值是 xml，则去除该值，若 doctype 值是 json，该值为空即可
    // xmlVersion：如果 doctype 值是 json，则去除该值，若 doctype 值是 xml，该值为空即可
    // type：语言自动检测时为 null，为 null 时可为空。英译中为 EN2ZH_CN，中译英为 ZH_CN2EN，日译中为 JA2ZH_CN，中译日为 ZH_CN2JA，韩译中为 KR2ZH_CN，中译韩为 ZH_CN2KR，中译法为 ZH_CN2FR，法译中为 FR2ZH_CN
    // keyform：mdict. + 版本号 + .手机平台。可为空
    // model：手机型号。可为空
    // mid：平台版本。可为空
    // imei：???。可为空
    // vendor：应用下载平台。可为空
    // screen：屏幕宽高。可为空
    // ssid：用户名。可为空
    // abtest：???。可为空

    // 请求方式说明
    // 请求方式：POST
    // 请求体：i
    // 请求格式：x-www-form-urlencoded

    /**
     * 指定Http请求方式为Post，传入baseURL以外的URL，向服务器提交需要的字段
     */
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<YDTranslation> post(@Field("i") String target);
}
