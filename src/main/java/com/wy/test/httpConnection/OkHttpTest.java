package com.wy.test.httpConnection;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp
 */
public class OkHttpTest {
    public static String get(String url) throws IOException{
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30L, TimeUnit.SECONDS)
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return null;
                    }
                })*/
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = okHttpClient.newCall(request);
        final StringBuilder stringBuilder = new StringBuilder();
       /* call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("开始请求");
                ResponseBody body = response.body();
                if (body != null){
                    stringBuilder.append(body.toString());
                }
            }
        });*/
       ResponseBody body = call.execute().body();
       if (body != null){
           stringBuilder.append(body.string());
       }
        return stringBuilder.toString();
    }

//    public static String post(String url, String requestBody, Headers headers) throws IOException{
//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .build();
//
//    }

}
