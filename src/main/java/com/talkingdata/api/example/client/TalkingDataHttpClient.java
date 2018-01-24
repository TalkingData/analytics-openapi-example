package com.talkingdata.api.example.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by xiaolong on 2015/4/17.
 */
public class TalkingDataHttpClient {
    /**
     * 发送http get请求到指定地址
     *
     * @param url       请求地址
     * @param parameter 查询携带的参数
     * @return 查询结果
     * @throws IOException
     */
    public static String doGet(String url, Map<String, Object> parameter) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        if (parameter != null) {
            url = url + "?";
            for (Map.Entry<String, Object> entry : parameter.entrySet()) {
                url += entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(), "UTF-8") + "&";
            }
            url = url.substring(0, url.length() - 1);
        }
        HttpGet httpgets = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpgets);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, "UTF-8");
    }

    /**
     * 发送http post请求到指定地址
     *
     * @param url  请求地址
     * @param params 查询携带的参数
     * @return 查询结果
     * @throws IOException
     */
    public static String doPost(String url, Object params) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(params.toString(), "UTF-8"));
        HttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity, "UTF-8");
    }

    public static void main(String[] args) throws IOException {
        System.out.println(TalkingDataHttpClient.doGet("", null));
    }
}
