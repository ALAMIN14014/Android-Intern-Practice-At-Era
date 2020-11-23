package com.example.getretrofitnestedobject;

import com.example.getretrofitnestedobject.Tutorial3Class.ArrayDesignClass;
import com.example.getretrofitnestedobject.Tutorial3Class.TeamClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OurRetrofitClient {

    //  @GET("continents")
    //Call<OurMainDataClass>getData(@Query("api_token") String token);


    // @GET("continents/{id}")
    //Call<tutorial2Class>getData(@Path("id") int id, @Query("api_token") String token);

    @GET("teams/{id}")
    Call<TeamClass> getData(@Path("id") int id, @Query("api_token") String token);

    @GET("players")
    Call<ArrayDesignClass> getPlayersData(@Query("api_tokern") String token, @Query("counry_id") int Cid);

}
