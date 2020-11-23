package com.example.myretrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Network.NetworkCall;
import Network.ResponseCallback;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NetworkCall myApiServic = new NetworkCall();

        myApiServic.showData();


    }


}