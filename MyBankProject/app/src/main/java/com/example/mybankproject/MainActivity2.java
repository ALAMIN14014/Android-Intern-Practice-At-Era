package com.example.mybankproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView fund_transfer_imageView;
    DrawerLayout drawerLayout;
    ImageView imageView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fund_transfer_imageView = findViewById(R.id.fundtransferid);
        //  toolbar = findViewById(R.id.toolbarid);
        //  setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation_id);
        bottomNavigationView.setSelectedItemId(R.id.home_bottom_id);


        drawerLayout = findViewById(R.id.drayerid);
        NavigationView navigationView = findViewById(R.id.navigationid);
        imageView = findViewById(R.id.humbergid);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_bottom_id:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nottification_bottom_id:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.account_bottom_id:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.setting_bottom_id:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.humbergid) {
                    if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {

                        drawerLayout.closeDrawer(Gravity.RIGHT);
                    } else {

                        drawerLayout.openDrawer(Gravity.RIGHT);

                    }

                }

            }
        });

        drawerLayout.setDrawerListener(new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close));


        fund_transfer_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, FundTransferActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
    //  public boolean onCreateOptionsMenu(Menu menu) {

    //    MenuInflater menuInflater = getMenuInflater();
    //  menuInflater.inflate(R.menu.burger_icon, menu);
    // return super.onCreateOptionsMenu(menu);
    //}

//    @Override
    //  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    //    if (item.getItemId() == R.id.burgerid) {
    //      if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {

    //        drawerLayout.closeDrawer(Gravity.RIGHT);
    //  } else {

    //    drawerLayout.openDrawer(Gravity.RIGHT);

    //}
    //}
    //return super.onOptionsItemSelected(item);
    //}


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.homeid) {
            Intent intent = new Intent(MainActivity2.this, HomeActivity.class);
            startActivity(intent);

        }

        return false;
    }
}