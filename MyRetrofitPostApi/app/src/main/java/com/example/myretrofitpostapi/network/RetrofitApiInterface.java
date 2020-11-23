package com.example.myretrofitpostapi.network;


import com.example.myretrofitpostapi.My_Info;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApiInterface {

    @POST("/user")
    Call<My_Info> checkNIDphoto(
            @Body RequestBody params
    );

}

