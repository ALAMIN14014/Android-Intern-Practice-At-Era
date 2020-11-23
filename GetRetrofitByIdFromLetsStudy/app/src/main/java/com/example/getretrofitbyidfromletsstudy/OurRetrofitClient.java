package com.example.getretrofitbyidfromletsstudy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OurRetrofitClient {

  //  @GET("continents")
    //Call<OurMainDataClass>getData(@Query("api_token") String token);


    @GET("continents/{id}")
    Call<tutorial2Class>getData(@Path ("id") int id, @Query("api_token") String token);


}
