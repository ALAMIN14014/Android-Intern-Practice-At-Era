package com.example.myretrofitpostapi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("user")
    Call<My_Info>PostData(@Body My_Info myinfo);

}
