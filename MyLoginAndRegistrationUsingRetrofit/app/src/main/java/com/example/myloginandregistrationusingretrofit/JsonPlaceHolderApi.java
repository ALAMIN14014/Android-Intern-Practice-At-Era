package com.example.myloginandregistrationusingretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("get")
    Call<List<User>> getpost();
}
