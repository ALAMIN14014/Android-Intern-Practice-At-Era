package com.example.myretrofitpostapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myretrofitpostapi.network.NetworkCall;
import com.example.myretrofitpostapi.network.ResponseCallback;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText editName, editAge, editPhone;
    private Button button;
    String name, age, phone;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editnameid);
        editAge = findViewById(R.id.editageid);
        editPhone = findViewById(R.id.editphoneid);
        button = findViewById(R.id.buttonid);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);


    }

    public void PostData(View view) {


        Map<String, Object> jsonParams = new ArrayMap<>();
//put something inside the map, could be null
        jsonParams.put("name", editName.getText().toString());
        jsonParams.put("age", editAge.getText().toString());
        jsonParams.put("phone", editPhone.getText().toString());

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
        NetworkCall myApiService;
        myApiService = new NetworkCall();
        myApiService.checkNIDphotoVerification(MainActivity.this, body, new ResponseCallback<My_Info>() {
            @Override
            public void onSuccess(My_Info data) {
                if (data != null) {
                    Toast.makeText(MainActivity.this, "Data Inserted" + data.getName(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onError(Throwable th) {
                Toast.makeText(MainActivity.this, th.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}