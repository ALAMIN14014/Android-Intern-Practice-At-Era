package com.example.mybankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameedit, phoneedit, emailedit;
    Button Updatebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameedit = findViewById(R.id.updatenameid);
        emailedit = findViewById(R.id.updateemailid);
        phoneedit = findViewById(R.id.updatephoneid);
        Updatebutton=findViewById(R.id.updatebuttonid);

        nameedit.setText(MainActivity.name );
        emailedit.setText(MainActivity.email );
        phoneedit.setText(MainActivity.phone);


    }

}