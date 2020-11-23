package com.example.androidarchitecturelivedatawithviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;
    private EditText text1, text2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//main activity sathe MainActivityModelView Connect korlm
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        text1 = findViewById(R.id.edittext1);
        text2 = findViewById(R.id.edittext2);
        button = findViewById(R.id.buttonid);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x1, x2;
                x1 = text1.getText().toString();
                x2 = text2.getText().toString();
                 mainActivityViewModel.additionFunction(x1, x2).observe(MainActivity.this, new Observer<String>() {
                     @Override
                     public void onChanged(String s) {
                         Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                     }
                 });


            }
        });
    }
}