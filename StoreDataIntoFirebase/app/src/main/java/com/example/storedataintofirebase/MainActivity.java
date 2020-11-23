package com.example.storedataintofirebase;

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
    private EditText nameEdit, ageEdit;
    private Button saveButton, loadButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = findViewById(R.id.nameid);
        ageEdit = findViewById(R.id.ageid);
        saveButton = findViewById(R.id.saveButtonid);
        loadButton = findViewById(R.id.loadButtonid);
        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });


    }

    private void saveData() {
        String name = nameEdit.getText().toString();
        String age = ageEdit.getText().toString();
        if (name.isEmpty()) {
            nameEdit.setError("Empty");
            nameEdit.requestFocus();
        } else if (age.isEmpty()) {

            ageEdit.setError("Empty");
            ageEdit.requestFocus();

        } else {
            String key = databaseReference.push().getKey();

            Student student = new Student(name, age);

            databaseReference.child(key).setValue(student);
            Toast.makeText(MainActivity.this, "data is inserted", Toast.LENGTH_LONG).show();
            nameEdit.setText("");
            ageEdit.setText("");

        }


    }
}