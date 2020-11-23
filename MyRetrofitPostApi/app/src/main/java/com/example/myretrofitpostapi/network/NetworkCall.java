package com.example.myretrofitpostapi.network;

import android.app.Activity;
import android.util.Log;

import com.example.myretrofitpostapi.My_Info;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetworkCall implements NetworkInterface {
    public static final String TAG = "XXX : " + NetworkCall.class.getSimpleName();
      private static RetrofitApiInterface retrofitApiInterface3 = RetrofitApiClient3.getClient().create(RetrofitApiInterface.class);


    @Override
    public void checkNIDphotoVerification(final Activity context, RequestBody params, final ResponseCallback<My_Info> callback) {
        Call<My_Info> call = retrofitApiInterface3.checkNIDphoto(params);
        call.enqueue(new Callback<My_Info>() {
            @Override
            public void onResponse(Call<My_Info> call, Response<My_Info> response) {

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
            public void onFailure(Call<My_Info> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                callback.onError(t);
            }
        });
    }


    }
