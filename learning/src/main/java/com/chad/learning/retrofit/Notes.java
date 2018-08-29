package com.chad.learning.retrofit;

public class Notes {
    /**
     *  Retrofit简介：基于OKHttp & 遵循Restful API设计风格，通过注解配置网络请求参数，
     *               支持同步/异步网络请求，支持多种数据的解析 & 序列化格式（Gson, Json, XML, Protobuf）,
     *               提供对RXJava的支持
     *
     *  Retrofit使用介绍：
     *                  步骤一：添加Retrofit库的依赖
     *                  步骤二：创建接收服务器返回数据的类
     *                  步骤三：创建用于描述网络请求的接口
     *                          注解类型：第一类：网络请求方法
     *                                    第二类：标记
     *                                    第三类：网络请求参数
     *                  步骤四：创建Retrofit实例
     *                          数据解析器
     *                          网络请求适配器
     *                  步骤五：创建网络请求接口实例
     *                  步骤六：发送网络请求（同步/异步）
     *                  步骤七：处理返回数据
     *
     *  注解说明：
     *          网络请求方法：@GET、@POST、@PUT、@DELETE、@PATH、@HEAD、@OPTIONS
     *                        分别对应Http中的网络请求方法，都接收一个网络地址URL（可以不指定，通过@HTTP设置）
     *
     *                        @HTTP用于替换以上7个注解的作用以及更多功能的扩展
     *                        通过属性method（网络请求的方法）、path（网络请求地址路径）、
     *                        hasBody（是否有请求体）进行设置
     *
     *          标记：
     *              @FormUrlEncoded：表示请求体是一个Form表单，每个键值对需要用@Filed来注解键名
     *                                随后的对象需要提供值
     *              @Multipart：表示请求体是一个支持文件上传的Form表单，每个键值需要用@Part来注解键名，
     *                           随后的对象需要提供值
     *              @Streaming：表示返回的数据以流的形式返回（如果没有使用该注解，
     *                           默认把数剧全部载入内存中，之后获取数据也是从内存中读取）
     *
     *          网络请求参数：
     *                      @Headers 添加请求头，作用于方法
     *                      @Header 添加不固定值的请求头，作用于方法的参数
     *                      @Body 用于非表单请求体，以POST方式传递自定义数据类型给服务器
     *                           （如果提交的是一个Map，那么作用相当于@Filed，
     *                             Map要经过FormBody处理成符合OkHttp格式的表单）
     *                      @Filed 向POST表单传入键值对，与@FormUrlEncoded配合使用
     *                      @FiledMap 向POST表单传入键值对，与@FormUrlEncoded配合使用
     *                      @Part 用于表单字段，与@Filed功能相同，但携带的参数类型更加丰富，
     *                            适用于有文件上传的情况，与@MultiPart配合使用
     *                      @PartMap 用于表单字段，与@Filed功能相同，但携带的参数类型更加丰富，
     *                               适用于有文件上传的情况，与@Multipart配合使用
     *                      @Query 用于表单字段，用于@GET方法的查询参数，使用方式同@Filed，
     *                             功能同@Filed，区别在于@Query数据体现在Url上，
     *                             @Filed数据体现在请求体上，但生成的数据是一致的
     *                      @QueryMap 用于表单字段，用于@Get方法的查询参数，使用方式同@FiledMap，
     *                                 功能同@FiledMap，区别在于@QueryMap数据体现在URL上，
     *                                 @FiledMap数据体现在请求体上，但生成的数据是一致的
     *                      @Path：URL缺省值
     *                      @URL：URL设置
     *
     *  数据解析器（Converter）：Retrofit支持多种数据解析方式，使用时需要在Gradle添加依赖
     *
     *  网路请求适配器（CallAdapter）：Retrofit支持多种网络请求适配器方式，guava、java8和rxJava
     *
     *  https://blog.csdn.net/carson_ho/article/details/73732076
     *
     */
}
