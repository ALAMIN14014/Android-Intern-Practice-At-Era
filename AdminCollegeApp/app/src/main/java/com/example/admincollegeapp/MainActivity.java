package com.example.admincollegeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView UploadNotice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UploadNotice = findViewById(R.id.addnoticeid);

        UploadNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.addnoticeid:
                Intent intent=new Intent(MainActivity.this,UploadNotice.class);
                startActivity(intent);
                break;

        }

    }
}