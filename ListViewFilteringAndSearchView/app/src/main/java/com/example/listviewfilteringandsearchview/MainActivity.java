package com.example.listviewfilteringandsearchview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listviewid);
        searchView=(SearchView) findViewById(R.id.searchviewid);
        final String[] countryName = getResources().getStringArray(R.array.country_name);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_view, R.id.textviewid, countryName);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = countryName[position];
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

            }
        });

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
    }
}