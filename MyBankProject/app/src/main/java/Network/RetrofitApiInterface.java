package Network;


import com.example.mybankproject.User;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApiInterface {

    @POST("/user")
    Call<User> checkNIDphoto(
            @Body RequestBody params
    );

}

