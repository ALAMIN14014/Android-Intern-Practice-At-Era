package com.example.myfirebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signinemaitedittext, signinpasswordedittext;
    private TextView signuptextview;
    private Button signinbutton;
    private FirebaseAuth mAuth;


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign in Activity");


        signinemaitedittext = findViewById(R.id.signinemailedittextid);
        signinpasswordedittext = findViewById(R.id.signinpasswordedittextid);
        signuptextview = findViewById(R.id.signuptextviewid);
        signinbutton = findViewById(R.id.signinbuttonid);
        progressBar = findViewById(R.id.progressbarid);

        signinbutton.setOnClickListener(this);
        signuptextview.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signinbuttonid:
                userlogin();
                break;
            case R.id.signuptextviewid:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void userlogin() {

        String email = signinemaitedittext.getText().toString().trim();
        String password = signinpasswordedittext.getText().toString().trim();
        if (email.isEmpty()) {
            signinemaitedittext.setError("Enter an email address");
            signinemaitedittext.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signinemaitedittext.setError("Enter a valid email address");
            signinemaitedittext.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            signinpasswordedittext.setError("Enter a password");
            signinpasswordedittext.requestFocus();
            return;
        }
        if (password.length() < 6) {
            signinpasswordedittext.setError("Minimum length of password should be 6");
            signinpasswordedittext.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}