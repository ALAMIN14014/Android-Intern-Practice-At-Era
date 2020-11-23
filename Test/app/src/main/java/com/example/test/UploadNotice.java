package com.example.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class UploadNotice extends AppCompatActivity {
    private CardView addImageCardView;
    private final int REQ = 1;
    private Bitmap bitmap;
    private ImageView noticeImageView;
    private Button uploadNotice;
    private EditText noticetitle;
   // private DatabaseReference reference;
    //private StorageReference storageReference;
    String downloadUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);

        addImageCardView = findViewById(R.id.addImageId);
        noticeImageView = findViewById(R.id.noticeImageViewId);
        noticetitle = findViewById(R.id.noticetitleid);
        uploadNotice = findViewById(R.id.uploadNoticeButtonid);
      //  reference = FirebaseDatabase.getInstance().getReference();
       // storageReference = FirebaseStorage.getInstance().getReference();


        addImageCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }


    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            noticeImageView.setImageBitmap(bitmap);
        }
    }
}