package com.example.mybankproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressvlaue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar=findViewById(R.id.prgressbarid);
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                dowork();
                startApp();
            }
        });
        thread.start();
    }

    private void startApp() {
        Intent intent=new Intent(SplashScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void dowork() {
        for(progressvlaue=20;progressvlaue<=100;progressvlaue=progressvlaue+20)
        {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progressvlaue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}