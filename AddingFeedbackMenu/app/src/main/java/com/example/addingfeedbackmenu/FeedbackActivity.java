package com.example.addingfeedbackmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sendbutton, clearbutton;
    private EditText nameedit, messageedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        sendbutton = findViewById(R.id.sendbuttonid);
        clearbutton = findViewById(R.id.clearbuttonid);
        nameedit = findViewById(R.id.nameedittextid);
        messageedit = findViewById(R.id.messageedittextid);
        sendbutton.setOnClickListener(this);
        clearbutton.setOnClickListener(this);

    }
        @Override
        public void onClick (View v){
            try {
                String name = nameedit.getText().toString();
                String mesg = messageedit.getText().toString();
                if (v.getId() == R.id.sendbuttonid) {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/email");
                    intent.putExtra(intent.EXTRA_EMAIL, new String[]{"alamin.ict14014@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App");
                    intent.putExtra(intent.EXTRA_TEXT, "Name " + name + "\n Message " + mesg);
                    startActivity(Intent.createChooser(intent, "Feedback"));

                } else if (v.getId() == R.id.clearbuttonid) {

                    nameedit.setText("");
                    messageedit.setText("");

                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Exception " + e, Toast.LENGTH_LONG).show();

            }
        }
    }
