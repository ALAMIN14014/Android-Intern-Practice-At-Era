package com.example.sharedpreferencestoringdatafromanisul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private EditText usernameedit, passwordedit;
    private Button savebutton, loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameedit = findViewById(R.id.usernameid);
        passwordedit = findViewById(R.id.passwordid);
        textView = findViewById(R.id.detailstextViewid);
        savebutton = findViewById(R.id.saveButtonid);
        loadButton = findViewById(R.id.loadButtonid);

        savebutton.setOnClickListener(this);
        loadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveButtonid) {


            String username = usernameedit.getText().toString();
            String password = passwordedit.getText().toString();
            if (username.equals("") || password.equals("")) {
                Toast.makeText(this, "Please enter Data", Toast.LENGTH_LONG).show();

            } else {
//writing Data
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey", username);
                editor.putString("passwordKey", password);
                editor.commit();
                usernameedit.setText("");
                passwordedit.setText("");
                Toast.makeText(this, "Data Inserted Successfually", Toast.LENGTH_LONG).show();

            }

        } else if (v.getId() == R.id.loadButtonid) {
            //read data
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")) {
                String username = sharedPreferences.getString("usernameKey", "Data Not found");
                String password = sharedPreferences.getString("passwordKey", "Data Not found");
                textView.setText(username + "\n" + password + "\n");
            }
            else {
                Toast.makeText(this, "Data not Found", Toast.LENGTH_LONG).show();


            }

        }

    }
}