package com.example.storedataintofirebase;

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

public class DetailsActivity extends AppCompatActivity {
    private ListView listView;
    private List<Student> studentListView;
    DatabaseReference databaseReference;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        studentListView = new ArrayList<>();
        listView = findViewById(R.id.listviewid);
        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        customAdapter = new CustomAdapter(DetailsActivity.this, studentListView);

    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentListView.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Student student = dataSnapshot1.getValue(Student.class);
                    studentListView.add(student);
                }
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}