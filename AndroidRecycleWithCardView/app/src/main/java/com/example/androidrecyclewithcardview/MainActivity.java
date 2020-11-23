package com.example.androidrecyclewithcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleviewid);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryName = new ArrayList<String>(Arrays.asList("Dhaka", "Khulna", "Barisal", "Rajshahi", "Chittagonj", "Jessore", "Noakhali","Tangail","Coz Bazar"));

        MyAdapter myAdapter = new MyAdapter(this, countryName);
        recyclerView.setAdapter(myAdapter);
    }
}