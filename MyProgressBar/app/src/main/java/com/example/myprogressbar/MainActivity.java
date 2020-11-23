package com.example.myprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    int progess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBarid);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        });
        thread.start();

    }

    public void doWork() {
        for (progess = 20; progess <= 100; progess = progess + 30) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}