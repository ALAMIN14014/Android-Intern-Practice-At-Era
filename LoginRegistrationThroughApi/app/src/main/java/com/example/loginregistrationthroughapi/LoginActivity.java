package com.example.loginregistrationthroughapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText loginemailedit, loginpasswordedit;
    private Button loginButton;
    private String loginEmail, loginPassword;
    private UserInterface userInterface;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginemailedit = findViewById(R.id.loginemailid);
        loginpasswordedit = findViewById(R.id.loginpasswordid);
        loginButton = findViewById(R.id.loginbuttonid);

        login();

    }

    private void login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.11.200.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userInterface = retrofit.create(UserInterface.class);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginEmail = loginemailedit.getText().toString();
                loginPassword = loginpasswordedit.getText().toString();

                Call<List<User>> call = userInterface.getEmailPassword();

                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                        if (!response.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, "not responsible Before Login You have to Register first", Toast.LENGTH_LONG).show();


                        } else {
                            int flag = 0;
                            List<User> users = response.body();
                            String name, email, phone, password;


                            for (User user : users) {

                                if (user.getEmail().equals(loginEmail) && user.getPassword().equals(loginPassword)) {
                                    name = user.getName();
                                    email = user.getEmail();
                                    phone = user.getPhone();
                                    password = user.getPassword();
                                    flag = 1;

                                    break;

                                }


                            }
                            if (flag == 1) {

                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                startActivity(intent);

                            } else {

                                Toast.makeText(LoginActivity.this, "Before Login You have to Register first", Toast.LENGTH_LONG).show();

                            }

                        }
                    }


                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


    }


}