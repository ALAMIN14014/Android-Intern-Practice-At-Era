package com.example.recycleviewbyrakibulislam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private EditText nameedit, phoneedit;
    private RecyclerView recyclerView;
    ContactAdapter adapter;
    ArrayList<Contact> mycontact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((nameedit.getText().toString().equals("")) || (phoneedit.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Fill the fields", Toast.LENGTH_LONG).show();
                } else {
                    String name = nameedit.getText().toString();
                    String phone = phoneedit.getText().toString();
                    Contact contact = new Contact(name, phone);
                    mycontact.add(contact);
                    adapter.notifyDataSetChanged();
                    nameedit.setText("" );
                    phoneedit.setText("");

                }
            }
        });

    }

    private void initialize() {

        submitButton = findViewById(R.id.buttonid);
        nameedit = findViewById(R.id.nameid);
        phoneedit = findViewById(R.id.phoneid);
        recyclerView = findViewById(R.id.recyclecviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(mycontact);
        recyclerView.setAdapter(adapter);


    }
}