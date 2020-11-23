package com.example.firebasestoredata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Student> studentList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        listView = findViewById(R.id.listviewid);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        studentList = new ArrayList<>();
        customAdapter = new CustomAdapter(DetailActivity.this, studentList);

    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    Student student = snapshot1.getValue(Student.class);
                    studentList.add(student);
                }
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}