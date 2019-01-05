package com.wy.test.httpConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * 第一种：java.net
 */
public class HttpConnect {
    public static String post(String urlStr, String data){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            JSONObject jsonObject = JSON.parseObject(data);
            dataOutputStream.writeBytes(jsonObject.toJSONString());
            dataOutputStream.flush();
            dataOutputStream.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                result.append(line);
            }
            reader.close();
            connection.disconnect();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(String strUrl){
        try {
            // 传入参数
//            String realUrl = strUrl;
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 在连接之前设置属性

            // Content-Type实体头用于向接收方指示实体的介质类型，指定HEAD方法送到接收方的实体介质类型，或GET方法发送的请求介质类型
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 设置打开与此URLConnection引用的资源的通信链接时使用的指定超时值（以毫秒为单位）
            conn.setConnectTimeout(10000);
            // 将读取超时设置为指定的超时时间，以毫秒为单位。
            // conn.setReadTimeout(60000);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);

            // 建立连接
            conn.connect();
            // 获取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            conn.disconnect();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
