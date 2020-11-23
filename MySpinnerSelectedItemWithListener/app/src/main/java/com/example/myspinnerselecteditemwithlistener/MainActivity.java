package com.example.myspinnerselecteditemwithlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private String[] country_name;
    private String[] population;
     int[] flags={R.drawable.argentina,R.drawable.brazil,R.drawable.england,R.drawable.india,R.drawable.argentina,
             R.drawable.brazil,R.drawable.england,R.drawable.india,R.drawable.argentina,R.drawable.brazil,
             R.drawable.england,R.drawable.india,R.drawable.england,R.drawable.india};
private  boolean isfirstselected=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinnerid);
        country_name = getResources().getStringArray(R.array.country_name);
        population = getResources().getStringArray(R.array.population);

        CustomAdapter customAdapter=new CustomAdapter(this,flags,country_name,population);
        spinner.setAdapter(customAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isfirstselected==true)
                {
                    isfirstselected=false;
                }else{
                Toast.makeText(getApplicationContext(), country_name[position]+" is selected ", Toast.LENGTH_LONG).show();
            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}