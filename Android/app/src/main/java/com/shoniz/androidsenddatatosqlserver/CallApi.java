package com.shoniz.androidsenddatatosqlserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class CallApi<T> {

    private String baseUrl;
    private String MIME_JSON = "application/json";
    protected CallApi(String url){
        this.baseUrl = url;
    }

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    Response Post(String route, Object object) throws IOException  {

        Gson gson = new Gson();
        RequestBody body = RequestBody.create(MediaType.parse(MIME_JSON), gson.toJson(object));

        Request request = new Request.Builder()
                .url(baseUrl + route)
                .post(body)
                .build();

        return okHttpClient.newCall(request).execute();

    }

    public T Get(String route, Object object) throws IOException {
        String MIME_JSON = "application/json";
        RequestBody body = RequestBody.create(MediaType.parse(MIME_JSON), new Gson().toJson(object));
        Request request = new Request.Builder()
                .url(baseUrl +route)
                .post(body)
                .build();
        Gson gson = new GsonBuilder()
                                .setLenient()
                                .create();
        Response r =  okHttpClient.newCall(request).execute();
        Type listType = new TypeToken<T>(){}.getType();
        return gson.fromJson(r.body().charStream(), listType);
    }
}
