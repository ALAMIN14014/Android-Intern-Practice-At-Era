package com.example.myoptionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settingid) {
            Toast.makeText(MainActivity.this, "Setting is selected", Toast.LENGTH_LONG).show();
            return true;
        }
        if (item.getItemId() == R.id.shareid) {
            Toast.makeText(MainActivity.this, "Share is selected", Toast.LENGTH_LONG).show();
            return true;
        }
        if (item.getItemId() == R.id.aboutusid) {
            Toast.makeText(MainActivity.this, "About is selected", Toast.LENGTH_LONG).show();
            return true;
        }
        if (item.getItemId() == R.id.feedbackid) {
            Toast.makeText(MainActivity.this, "Feedback is selected", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}