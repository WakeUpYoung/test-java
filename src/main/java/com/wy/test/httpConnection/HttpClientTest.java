package com.wy.test.httpConnection;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * apache HttpClient
 */
public class HttpClientTest {

    public static String get(String urlStr){
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(30000)
                .build();
        HttpGet get = new HttpGet(urlStr);
        get.setConfig(config);
        String result = "";
        try {
            CloseableHttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String post(String urlStr, String jsonStr){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(urlStr);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(30*1000)
                .build();
        post.setConfig(requestConfig);
        post.setHeader("Content-Type","application/json");
        post.setEntity(new StringEntity(jsonStr, ContentType.create("application/json", "utf-8")));
        String result = "";
        try {
            CloseableHttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200){
                result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
