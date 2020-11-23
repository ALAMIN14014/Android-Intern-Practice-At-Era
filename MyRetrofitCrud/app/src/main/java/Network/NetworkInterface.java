package Network;

import android.app.Activity;


import com.example.myretrofitcrud.User;

import okhttp3.RequestBody;

public interface NetworkInterface {


     void checkNIDphotoVerification(Activity context, RequestBody params, final ResponseCallback<User> callback);
     void  showData();

}
