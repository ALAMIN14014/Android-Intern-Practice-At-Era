package com.example.firebasestoredata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText nameedit, ageedit;
    private Button savebutton, showbutton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameedit = findViewById(R.id.nameeditid);
        ageedit = findViewById(R.id.ageeditid);
        savebutton = findViewById(R.id.buttontid);
        showbutton = findViewById(R.id.showbuttontid);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        showbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        String name = nameedit.getText().toString().trim();
        String age = ageedit.getText().toString().trim();

        Student student = new Student(name, age);

        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(), "Student info is added", Toast.LENGTH_LONG).show();
        nameedit.setText("");
        ageedit.setText("");

    }
}

