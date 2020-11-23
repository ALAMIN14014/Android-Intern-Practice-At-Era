package com.example.myretrofitpostapi.network;

import android.app.Activity;

import com.example.myretrofitpostapi.My_Info;

import okhttp3.RequestBody;

public interface NetworkInterface {


     void checkNIDphotoVerification(Activity context, RequestBody params, final ResponseCallback<My_Info> callback);

}
