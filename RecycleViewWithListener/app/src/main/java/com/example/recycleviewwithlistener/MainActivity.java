package com.example.recycleviewwithlistener;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] titles, desc;
    MyAdapter myAdapter;

    int[] imgaes = {R.drawable.bangladesh, R.drawable.bangladesh, R.drawable.bangladesh,
            R.drawable.bangladesh, R.drawable.bangladesh, R.drawable.bangladesh,
            R.drawable.bangladesh, R.drawable.bangladesh, R.drawable.bangladesh, R.drawable.bangladesh,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleviewid);


        titles = getResources().getStringArray(R.array.country_name);
        desc = getResources().getStringArray(R.array.counrty_desc);

        myAdapter = new MyAdapter(this, titles, desc, imgaes);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}