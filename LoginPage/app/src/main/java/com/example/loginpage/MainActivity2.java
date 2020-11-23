package com.example.loginpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myDb = new DatabaseHelper(this);
        viewAll();

    }

    public void viewAll() {

        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showmessage("Error", "Nothing found");

        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                buffer.append("Id :" + res.getString(0) + "\n");
                buffer.append("Name :" + res.getString(1) + "\n");
                buffer.append("Email :" + res.getString(2) + "\n");
                buffer.append("Username :" + res.getString(3) + "\n");
                buffer.append("Password :" + res.getString(4) + "\n\n");
            }
            showmessage("User Profile", buffer.toString());

        }

    }

    public void showmessage(String Title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.show();

    }

}