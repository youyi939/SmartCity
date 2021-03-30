package com.example.smartcity_test2.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KenUtils {


    /**
     * 获取网络资源
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getJson(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(url).method("GET", null).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }



}
