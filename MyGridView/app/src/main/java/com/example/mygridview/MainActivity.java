package com.example.mygridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] flags = {R.drawable.argentina, R.drawable.brazil, R.drawable.england, R.drawable.india,R.drawable.argentina,
            R.drawable.brazil, R.drawable.england, R.drawable.india,R.drawable.argentina, R.drawable.brazil,
            R.drawable.england, R.drawable.india,R.drawable.england, R.drawable.india};

    String[] countryNames;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryNames = getResources().getStringArray(R.array.country_name);
        gridView = (GridView) findViewById(R.id.gridviewid);
        CustomAdapter adapter = new CustomAdapter(this, countryNames, flags);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String vales=countryNames[position];
                Toast.makeText(MainActivity.this, vales, Toast.LENGTH_LONG).show();
            }
        });

    }
}