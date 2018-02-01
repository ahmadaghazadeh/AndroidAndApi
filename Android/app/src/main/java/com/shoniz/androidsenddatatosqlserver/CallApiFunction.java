package com.shoniz.androidsenddatatosqlserver;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Response;

public class CallApiFunction {
    public static Employee SaveEmployee(Employee employee) throws IOException {


        String url="http://192.168.0.196:8822/";
        String apiName="api/SaveEmployee";
        Response response = new CallApi(url)
                .Post(apiName, employee);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return gson.fromJson(response.body().charStream(),
                new TypeToken<Employee>() {
                }.getType());
    }
}
