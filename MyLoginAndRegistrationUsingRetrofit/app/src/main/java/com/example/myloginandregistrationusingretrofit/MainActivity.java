package com.example.myloginandregistrationusingretrofit;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.nameid);

//https://httpbin.org/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:192.168.100.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<User>> call = jsonPlaceHolderApi.getpost();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {

                    textView.setText("code : " + response.code());
                }

                List<User> users = response.body();
                if (response.body() == null) {
                    Toast.makeText(getApplicationContext(), "null found", Toast.LENGTH_LONG).show();
                }
                for (User user: users) {
                    String content = "";
                    content += "ID " + user.getId() + " \n";
                    content += "Name " + user.getName() + " \n";
                    content += "Email " + user.getEmail() + " \n";
                    content += "Phone " + user.getPhone() + " \n\n";
                    content += "Password " + user.getPassword() + " \n\n";

                    textView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                textView.setText(t.getMessage());
            }
        });

    }
}