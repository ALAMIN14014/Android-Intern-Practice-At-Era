package com.example.mybankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView logintext, registertext;
    private EditText loginemailedit, loginpasswordedit;
    private UserInterface userInterface;
    private String loginemail, loginPassword;
    public static String name, email, phone, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logintext = (TextView) findViewById(R.id.loginid);
        registertext = (TextView) findViewById(R.id.registerid);
        loginemailedit = findViewById(R.id.loginemailid);
        loginpasswordedit = findViewById(R.id.loginpasswordid);

        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });


        login();


    }

    private void login() {



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.11.200.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        userInterface = retrofit.create(UserInterface.class);

        logintext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                loginemail = loginemailedit.getText().toString();
                loginPassword = loginpasswordedit.getText().toString();

                Call<List<User>> call = userInterface.getEmailPassword();

                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                        if (!response.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "not responsible Before Login You have to Register first", Toast.LENGTH_LONG).show();


                        } else {
                            int flag = 0;

                            List<User> users = response.body();


                            for (User user : users) {

                                Log.d("email", user.getEmail());
                                Log.d("password", user.getPassword());
                                if (user.getEmail().equals(loginemail) && user.getPassword().equals(loginPassword)) {
                                    name = user.getName();
                                    email = user.getEmail();
                                    phone = user.getPhone();
                                    password = user.getPassword();
                                    flag = 1;

                                    break;

                                }


                            }
                            if (flag == 1) {


                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(intent);

                            } else {

                                Toast.makeText(MainActivity.this, "Before Login You have to Register first", Toast.LENGTH_LONG).show();

                            }

                        }
                    }


                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


    }


}