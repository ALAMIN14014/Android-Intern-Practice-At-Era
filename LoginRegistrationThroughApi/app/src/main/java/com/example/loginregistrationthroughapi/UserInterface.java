package com.example.loginregistrationthroughapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInterface {

    @POST("user")
    Call<User>Submit(@Body User user);

    @GET("user")
    Call<List<User>> getEmailPassword();



}
