package com.example.retorofiteasypost;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText nameEdit, emailEdit, phoneEdit, passwordEdit, confirmPasswordEdit;
    private Button button;
    private String name, email, phone, password, confirmPassword;
    private UserInterface userInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = findViewById(R.id.nameid);
        emailEdit = findViewById(R.id.emailid);
        phoneEdit = findViewById(R.id.phoneid);
        passwordEdit = findViewById(R.id.passwordid);
        confirmPasswordEdit = findViewById(R.id.confirmpasswordid);
        button = findViewById(R.id.buttonid);
        SignUp();

    }

    private void SignUp() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        userInterface = retrofit.create(UserInterface.class);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameEdit.getText().toString();
                email = emailEdit.getText().toString();
                phone = phoneEdit.getText().toString();
                password = passwordEdit.getText().toString();
                confirmPassword = confirmPasswordEdit.getText().toString();

                User user = new User(name, email, phone, password);

                Call<User> call = userInterface.Submit(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Registration not Successful", Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


    }


}