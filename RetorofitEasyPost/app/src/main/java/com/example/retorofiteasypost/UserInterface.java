package com.example.retorofiteasypost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {

    @POST("user")
    Call<User>Submit(@Body User user);


}
