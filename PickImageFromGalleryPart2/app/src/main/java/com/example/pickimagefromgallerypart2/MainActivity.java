package com.example.pickimagefromgallerypart2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    private static final int image_pic_code = 100;
    private static final int image_permission_code = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageviewid);
        button = findViewById(R.id.buttonid);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//permission not granted. request it
                        String[] permisssion = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show pop up for run time permission
                        requestPermissions(permisssion, image_permission_code);
                    } else {
//permission already granted
                        pickImagefromGallry();
                    }

                } else {

//system os is less than marsmellow
                    Toast.makeText(getApplicationContext(), "Please update android version", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void pickImagefromGallry() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, image_pic_code);

    }

    //hald;e result of runtime permission

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {



        switch (requestCode) {

            case image_permission_code: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImagefromGallry();

                } else {
//permission was denied
                    Toast.makeText(this, "Permission denied ...! ", Toast.LENGTH_LONG).show();

                }
            }

        }

    }


//handle the result of the picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == image_pic_code) {
            imageView.setImageURI(data.getData());
        }
    }
}