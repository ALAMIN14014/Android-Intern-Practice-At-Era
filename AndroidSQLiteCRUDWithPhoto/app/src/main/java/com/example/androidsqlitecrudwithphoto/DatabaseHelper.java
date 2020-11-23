package com.example.androidsqlitecrudwithphoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public long insertInfo(String name, String age, String phone, String image, String addTimeStamp, String updateTimeStamp) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.C_NAME, name);
        contentValues.put(Constants.C_AGE, age);
        contentValues.put(Constants.C_PHONE, phone);
        contentValues.put(Constants.C_IMAGE, image);
        contentValues.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        contentValues.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        long id = sqLiteDatabase.insert(Constants.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

}
