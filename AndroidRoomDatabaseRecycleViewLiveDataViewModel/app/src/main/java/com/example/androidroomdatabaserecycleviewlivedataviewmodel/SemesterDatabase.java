package com.example.androidroomdatabaserecycleviewlivedataviewmodel;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Semester.class}, version = 1, exportSchema = false)
public abstract class SemesterDatabase extends RoomDatabase {

    public abstract SemesterDao semesterDao();

    private static volatile SemesterDatabase instance;


    static SemesterDatabase getDatabase(final Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), SemesterDatabase.class, "word_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(instance).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SemesterDao mDao;

        PopulateDbAsync(SemesterDatabase db) {
            mDao = db.semesterDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            mDao.deleteAll();
//            Semister semister = new Semister(3.25,4.00,"mysem");
//            mDao.insertSemister(semister);
            return null;

        }
    }
}
