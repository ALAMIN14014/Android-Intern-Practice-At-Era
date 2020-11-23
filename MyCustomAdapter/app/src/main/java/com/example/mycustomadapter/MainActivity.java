package com.example.mycustomadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    String[] countryName;
    private LayoutInflater inflater;

    private int[] flags = {R.drawable.argentina, R.drawable.brazil, R.drawable.england, R.drawable.india, R.drawable.india, R.drawable.argentina,

            R.drawable.brazil, R.drawable.england, R.drawable.india
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listviewid);

        countryName = getResources().getStringArray(R.array.country_name);

        CustomAdapter adapter = new CustomAdapter(this, countryName, flags);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String values = countryName[position];
                Toast.makeText(MainActivity.this, values, Toast.LENGTH_LONG).show();
            }
        });

    }
}