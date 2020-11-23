package com.example.androidsqlitecrudwithphoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.net.URI;

public class AddRecordActivity extends AppCompatActivity {

    private ImageView pimageView;
    private EditText pNameEt, pAgeEt, pPhoneEt;
    Button saveInfoBt;
    ActionBar actionBar;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private java.lang.String[] cameraPermission;
    private java.lang.String[] storagePermission;

    private Uri imageUri;
    private Object String;
    private String name, age, phone, timestamp;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Information");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        pimageView = findViewById(R.id.imageviewid);
        pNameEt = findViewById(R.id.nameid);
        pAgeEt = findViewById(R.id.ageid);
        pPhoneEt = findViewById(R.id.phoneid);

        cameraPermission = new String[]{
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        dbHelper = new DatabaseHelper(this);

        pimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }
        });

        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();

            }
        });

    }

    private void getData() {
        name = "" + pNameEt.getText().toString().trim();
        age = "" + pAgeEt.getText().toString().trim();
        phone = "" + pPhoneEt.getText().toString().trim();
        timestamp = "" + System.currentTimeMillis();

        long id = dbHelper.insertInfo("" + name, "" + age, "" + age, "" + imageUri, "" + timestamp, "" + timestamp);
        Toast.makeText(this, "recorded to this id " + id, Toast.LENGTH_LONG).show();
    }

    private void imagePickDialog() {
        String[] options = {"Camera", "Gallery"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select for image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCamerapermission()) {
                        requestCamerapermission();
                    } else {
                        pickfromCamera();

                    }
                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requeststoragepermission();
                    } else {
                        pickFromStorage();
                    }
                }


            }
        });
        builder.create().show();


    }

    private void pickFromStorage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);

    }

    private void pickfromCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Image title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Image Description");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_GALLERY_CODE);

    }


    private boolean checkStoragePermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);


        return result;
    }

    private void requeststoragepermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);


    }

    private boolean checkCamerapermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requestCamerapermission() {


        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull java.lang.String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted) {
                        pickfromCamera();
                    } else {
                        Toast.makeText(this, "Camera permission required", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        pickFromStorage();
                    } else {
                        Toast.makeText(this, "Storage permission required", Toast.LENGTH_LONG).show();

                    }
                }


            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            } else if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (requestCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    pimageView.setImageURI(resultUri);


                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(this, "" + error, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //this function will move add record activity to main activity when back button pressed
        onBackPressed();
        return super.onSupportNavigateUp();

    }


}
