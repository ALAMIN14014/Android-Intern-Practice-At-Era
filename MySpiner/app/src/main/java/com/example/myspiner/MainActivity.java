package com.example.myspiner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] countryName;
    private Button button;
    private Spinner spinner;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryName = getResources().getStringArray(R.array.country_name);

        spinner = findViewById(R.id.spinnerid);
        button = findViewById(R.id.buttonid);
        textView = findViewById(R.id.textviewid);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_view, R.id.textviewampleid, countryName);
        spinner.setAdapter(adapter);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String value=spinner.getSelectedItem().toString();
        textView.setText(value);
    }
});

    }
}