package com.wy.test.httpConnection;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) {
        String url = "http://www.baidu.com";

        // HttpConnection
//        String result = HttpConnect.get("http://www.baidu.com");
//        System.out.println(result);

        // HttpClient
//        System.out.println(HttpClientTest.get(url));

        // OkHttp3
        try{

            System.out.println(OkHttpTest.get(url));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
