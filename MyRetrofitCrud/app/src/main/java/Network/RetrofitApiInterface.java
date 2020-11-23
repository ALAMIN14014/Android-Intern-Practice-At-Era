package Network;


import com.example.myretrofitcrud.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApiInterface {

    @POST("/addUser")
    Call<User> checkNIDphoto(
            @Body RequestBody params
    );


    @GET("/users")
    Call<User> getUserData();

}

