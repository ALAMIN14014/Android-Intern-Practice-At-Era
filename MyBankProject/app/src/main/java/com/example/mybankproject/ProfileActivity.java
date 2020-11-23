package com.example.mybankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView nametextView, emailtextview, phonetextview;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nametextView = findViewById(R.id.nametextviewid);
        emailtextview = findViewById(R.id.emailtextviewid);
        phonetextview = findViewById(R.id.phonetextviewid);
        edit = findViewById(R.id.editid);

        nametextView.setText(MainActivity.name);
        emailtextview.setText(MainActivity.email);
        phonetextview.setText(MainActivity.phone);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ProfileActivity.this, UpdateActivity.class);
                startActivity(intent);


            }
        });


    }
}