package com.example.myretrofitcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Map;

import Network.NetworkCall;
import Network.ResponseCallback;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    private EditText nameEdit, phoneEdit, passwordEdit, emailEdit;
    private Button signUpButton, loginbutton;
    // String name, phone, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = findViewById(R.id.signupnameid);
        phoneEdit = findViewById(R.id.signupmobileid);
        passwordEdit = findViewById(R.id.signuppasswordid);
        emailEdit = findViewById(R.id.signupemailid);

        signUpButton = findViewById(R.id.signupbuttonid);
        loginbutton = findViewById(R.id.loginbuttonid);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String, Object> jsonParams = new ArrayMap<>();
                jsonParams.put("name", nameEdit.getText().toString());
                jsonParams.put("mobile", phoneEdit.getText().toString());
                jsonParams.put("password", passwordEdit.getText().toString());
                jsonParams.put("imagelink", emailEdit.getText().toString());
                Log.d("Email", emailEdit.getText().toString());

                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());
                NetworkCall myApiService;
                myApiService = new NetworkCall();
                myApiService.checkNIDphotoVerification(MainActivity.this, body, new ResponseCallback<User>() {
                    @Override
                    public void onSuccess(User data) {
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
        });

    }


}