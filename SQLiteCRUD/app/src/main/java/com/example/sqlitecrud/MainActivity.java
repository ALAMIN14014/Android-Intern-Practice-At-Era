package com.example.sqlitecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editname, editsurname, editmarks;
    Button addbutton, viewall;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editname = (EditText) findViewById(R.id.nameedittextid);
        editsurname = (EditText) findViewById(R.id.surnameedittextid);
        editmarks = (EditText) findViewById(R.id.markedittextid);
        addbutton = (Button) findViewById(R.id.addbuttonid);
        viewall = (Button) findViewById(R.id.viewbuttonid);
        myDb = new DatabaseHelper(this);

        AddData();
        viewAll();


    }


    public void AddData() {
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editname.getText().toString();
                String surname = editname.getText().toString();
                String marks = editmarks.getText().toString();
                boolean isInserted = myDb.insertData(name, surname, marks);
                if (isInserted == true) {
                    Toast.makeText(getApplicationContext(), "Data is inserted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Data is not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewAll() {
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showmessage("Error", "Nothing found");

                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {

                        buffer.append("ID :" + res.getString(0) + "\n");
                        buffer.append("NAME :" + res.getString(1) + "\n");
                        buffer.append("SURNAME :" + res.getString(2) + "\n");
                        buffer.append("MARKS :" + res.getString(3) + "\n\n");
                    }
                    showmessage("Data", buffer.toString());

                }
            }
        });


    }


    public void showmessage(String Title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.show();

    }


}