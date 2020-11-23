package com.example.searchviewactionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private String[] countryName;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.mylistviewid);
        countryName = getResources().getStringArray(R.array.country_name);

        adapter = new ArrayAdapter<String>(this, R.layout.sample_view, R.id.textviewid, countryName);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        MenuItem menuItem = menu.findItem(R.id.searchviewid);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String values = adapter.getItem(position);
        Toast.makeText(getApplicationContext(), values, Toast.LENGTH_LONG).show();

    }
}