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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signupemaitedittext, signuppasswordedittext;
    private TextView signintextview;
    private Button signupbutton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign up Activity");

        signupemaitedittext = findViewById(R.id.signupemailedittextid);
        signuppasswordedittext = findViewById(R.id.signuppasswordedittextid);
        signintextview = findViewById(R.id.signintextviewid);
        signupbutton = findViewById(R.id.signupbuttonid);

        mAuth = FirebaseAuth.getInstance();
        signupbutton.setOnClickListener(this);
        signintextview.setOnClickListener(this);
        progressBar = findViewById(R.id.signupprogressbarid);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signupbuttonid:
                userRegister();
                break;
            case R.id.signintextviewid:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void userRegister() {

        String email = signupemaitedittext.getText().toString().trim();
        String password = signuppasswordedittext.getText().toString().trim();
        if (email.isEmpty()) {
            signupemaitedittext.setError("Enter an email address");
            signupemaitedittext.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupemaitedittext.setError("Enter a valid email address");
            signupemaitedittext.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            signuppasswordedittext.setError("Enter a password");
            signuppasswordedittext.requestFocus();
            return;
        }
        if (password.length() < 6) {
            signuppasswordedittext.setError("Minimum length of password should be 6");
            signuppasswordedittext.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(getApplicationContext(), "Registeration is already registered", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


    }
}