package Network;

import android.app.Activity;
import android.util.Log;

import com.example.mybankproject.User;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetworkCall implements NetworkInterface {
    public static final String TAG = "XXX : " + NetworkCall.class.getSimpleName();
      private static RetrofitApiInterface retrofitApiInterface3 = RetrofitApiClient3.getClient().create(RetrofitApiInterface.class);


    @Override
    public void checkNIDphotoVerification(final Activity context, RequestBody params, final ResponseCallback<User> callback) {
        Call<User> call = retrofitApiInterface3.checkNIDphoto(params);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.message());
                    Log.d(TAG, "onResponse: " + response.body());
                    callback.onSuccess(response.body());
                }else if (response.code()==400) {
                     Log.d(TAG, "onResponse: 400" + response.message());

                      callback.onSuccess(response.body());
                } else {
                    callback.onError(new Throwable(response.message()));
                    Log.e("logcheck",response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                callback.onError(t);
            }
        });
    }

    }
