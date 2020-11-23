package com.example.mycardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView schoolCardView,restaurentCardView,libraryCardView,hospitalCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        schoolCardView=findViewById(R.id.schoolcardviewid);
        libraryCardView=findViewById(R.id.librarycardviewid);
        schoolCardView=findViewById(R.id.hospitalcardviewid);
        hospitalCardView=findViewById(R.id.restaurentcardviewid);

        schoolCardView.setOnClickListener(this);
        libraryCardView.setOnClickListener(this);
        hospitalCardView.setOnClickListener(this);
        restaurentCardView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.schoolcardviewid:
            {
                intent=new Intent(this,schoolActivity.class);
            }
            case R.id.hospitalcardviewid:
            {
                intent=new Intent(this,hospitalActivity.class);
            }
            case R.id.librarycardviewid:
            {
                intent=new Intent(this,libraryActivity.class);
            }
            case R.id.restaurentcardviewid:
            {
                intent=new Intent(this,restaurentActivity.class);
            }

        }


    }
}