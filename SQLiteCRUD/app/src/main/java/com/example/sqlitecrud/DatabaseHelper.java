package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Student.db";
    public static String TABLE_NAME = "student_table";
    public static String COL_1 = "ID";
    public static String COL_2 = "NAME";
    public static String COL_3 = "SURNAME";
    public static String COL_4 = "MARKS";
    public Context context;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " VARCHAR(255)," + COL_3 + " VARCHAR(255)," + COL_4 + " INTEGR);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 15);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        try {
            Toast.makeText(context, "On create is called", Toast.LENGTH_LONG).show();

            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context, "On Upgrade is called", Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);

            onCreate(db);

        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_LONG).show();

        }
    }

    public boolean insertData(String name, String surname, String marks) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;

        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);

        return res;
    }

}
