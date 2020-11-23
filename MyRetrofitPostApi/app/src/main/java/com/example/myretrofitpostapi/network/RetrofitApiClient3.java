package com.example.myretrofitpostapi.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient3 {
    private static final String BASE_URL = "http://10.11.200.1:8080/"; //address of your remote server. Here I used localhost
    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitApiClient3() {
    } // So that nobody can create an object with constructor

    static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(520, TimeUnit.SECONDS)
            .readTimeout(520, TimeUnit.SECONDS)
            .writeTimeout(520, TimeUnit.SECONDS)
            .build();

    public static synchronized Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            ;
        }
        return retrofit;
    }
}
